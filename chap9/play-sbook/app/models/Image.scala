package models

import play.api.Play.current
import play.api.libs.json._
import play.api.db.DB
import anorm._
import anorm.SqlParser._

import java.util.Date
import java.io.File

import org.joda.time.LocalTime

case class Image(
  internalId:Pk[Long] = NotAssigned,
  user:User,
  timestamp:LocalTime,
  caption:String,
  filePath:String
) {
  lazy val pic = new File(filePath)

  def save(chat:Chat) =
  DB.withTransaction { implicit conn =>
    internalId.flatMap { e => Image.load(e) } match {
      case None => create(chat)
      case _ => update(chat)
    }
  }


  def create(chat:Chat) = DB.withConnection { implicit conn =>
    val id = scala.util.Random.nextLong
    SQL(
      """
        INSERT INTO image(
          internal_id,
          chat_id,
          user_email,
          timestamp,
          caption,
          file_path
        )
        VALUES (
          {internalId},
          {chatId},
          {userId},
          {time},
          {caption},
          {filePath}
        )
      """
    ).on(
      'internalId -> id,
      'userId -> user.email.get,
      'chatId -> chat.internalId.get,
      'time -> timestamp.toDateTimeToday.toDate,
      'caption -> caption,
      'filePath -> filePath
    ).executeUpdate()
    this.copy(internalId = Id(id))
  }

  def update(chat:Chat) = DB.withConnection { implicit conn =>
    SQL(
      """
        UPDATE image
        SET
          chat_id = {chatId},
          user_email = {userId},
          timestamp = {time},
          caption = {caption},
          file_path = {filePath},
        WHERE
          internal_id = {internalId}
      """
    ).on(
      'internalId -> internalId.get,
      'chatId -> chat.internalId.get,
      'userId -> user.email.get,
      'time -> timestamp.toDateTimeToday.toDate,
      'caption -> caption,
      'filePath -> filePath
    ).executeUpdate()
    this
  }

}

object Image {
  val ImageType = Seq(
    "image/png",
    "image/jpeg"
  )


  /**
   * Parse a Image from a ResultSet
   */
  val simple = {
    get[Pk[Long]]("image.internal_id") ~
    get[Pk[String]]("image.user_email") ~
    get[Date]("image.timestamp") ~
    get[String]("image.caption") ~
    get[String]("image.file_path") map {
      case id~userid~timestamp~caption~filePath =>
        Image(id, User.load(userid.get).get, new LocalTime(timestamp.getTime), caption, filePath)
    }
  }

  def load(id:Long):Option[Image] =
    DB.withConnection { implicit conn =>
      SQL("""
        SELECT
          *
        FROM
          image
        WHERE
          internal_id = {id}
      """).on('id -> id).as(Image.simple.singleOpt)
    }

  implicit object ImageFormat extends Format[Image] {
     def reads (json: JsValue): JsResult[Image] =
      JsSuccess(Image(
        Id((json \ "internalId").as[String].toLong),
        User.load((json \ "user").as[String]).get,
        new LocalTime((json \ "timestamp").as[Long]),
        (json \ "caption").as[String],
        (json \ "filePath").as[String]
      ))

    def writes (i: Image): JsValue =
      JsObject(Seq(
        "internalId" -> JsString(i.internalId.get.toString),
        "user" -> JsString(i.user.email.get),
        "timestamp" -> JsNumber(i.timestamp.millisOfDay.get),
        "caption" -> JsString(i.caption),
        "filePath" -> JsString(i.filePath)
      ))
  }

}