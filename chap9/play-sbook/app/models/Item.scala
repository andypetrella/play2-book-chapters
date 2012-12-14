package models

import play.api.Play.current
import play.api.db.DB
import anorm._
import anorm.SqlParser._
import play.api.libs.json._

import java.util.Date
import org.joda.time.LocalTime


case class Item(
  internalId:Pk[Long] = NotAssigned,
  user:User,
  timestamp:LocalTime,
  message:String
) {

  def save(chat:Chat) =
  DB.withTransaction { implicit conn =>
    internalId.flatMap { e => Item.load(e) } match {
      case None => create(chat)
      case _ => update(chat)
    }
  }


  def create(chat:Chat) = DB.withConnection { implicit conn =>
    val id = scala.util.Random.nextLong
    SQL(
      """
        INSERT INTO item(
          internal_id,
          user_email,
          chat_id,
          timestamp,
          message
        )
        VALUES (
          {internalId},
          {userId},
          {chatId},
          {time},
          {message}
        )
      """
    ).on(
      'internalId -> id,
      'userId -> user.email.get,
      'chatId -> chat.internalId.get,
      'time -> timestamp.toDateTimeToday.toDate,
      'message -> message
    ).executeUpdate()
    this.copy(internalId = Id(id))
  }

  def update(chat:Chat) = DB.withConnection { implicit conn =>
    SQL(
      """
        UPDATE item
        SET
          user_email = {userId},
          chat_id = {chatId},
          timestamp = {time},
          message = {message},
        WHERE
          internal_id = {internalId}
      """
    ).on(
      'internalId -> internalId.get,
      'chatId -> chat.internalId.get,
      'userId -> user.email.get,
      'time -> timestamp.toDateTimeToday.toDate,
      'message -> message
    ).executeUpdate()
    this
  }

}

object Item {

  /**
   * Parse a Item from a ResultSet
   */
  val simple = {
    get[Pk[Long]]("item.internal_id") ~
    get[Pk[String]]("item.user_email") ~
    get[Date]("item.timestamp") ~
    get[String]("item.message") map {
      case id~userid~timestamp~message =>
        Item(id, User.load(userid.get).get, new LocalTime(timestamp.getTime), message)
    }
  }

  def load(id:Long):Option[Item] =
    DB.withConnection { implicit conn =>
      SQL("""
        SELECT
          *
        FROM
          item
        WHERE
          internal_id = {id}
      """).on('id -> id).as(Item.simple.singleOpt)
    }

  implicit object ItemFormat extends Format[Item] {
    def reads (json: JsValue): Item =
      Item(
        Id((json \ "internalId").as[String].toLong),
        User.load((json \ "user").as[String]).get,
        new LocalTime((json \ "timestamp").as[Long]),
        (json \ "message").as[String]
      )

    def writes (i: Item): JsValue =
      JsObject(Seq(
        "internalId" -> JsString(i.internalId.get.toString),
        "user" -> JsString(i.user.email.get),
        "timestamp" -> JsNumber(i.timestamp.toDateTimeToday.toDate.getTime),
        "message" -> JsString(i.message)
      ))
  }

}