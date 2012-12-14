package models

import play.api.Play.current
import play.api.db.DB
import anorm._
import anorm.SqlParser._

import java.util.Date
import org.joda.time.LocalDate;

case class Chat(
  internalId:Pk[Long] = NotAssigned,
  date:LocalDate,
  occurrence:Int,
  topic:String,
  items:Seq[Item],
  images:Seq[Image]
) {

  def save =
    DB.withTransaction { implicit conn =>
      internalId.flatMap { e => Chat.load(e) } match {
        case None => create
        case _ => update
      }
    }

  def create = DB.withConnection { implicit conn =>
    val id = scala.util.Random.nextLong
    SQL(
      """
        INSERT INTO chat(
          internal_id,
          date,
          occurrence,
          topic
        )
        VALUES (
          {internalId},
          {date},
          {occurrence},
          {topic}
        )
      """
    ).on(
      'internalId -> id,
      'date -> date.toDate,
      'occurrence -> occurrence,
      'topic -> topic
    ).executeUpdate()

    this.copy(internalId = Id(id))
  }

  def update = DB.withConnection { implicit conn =>
    SQL(
      """
        UPDATE user
        SET
          date = {date},
          occurrence = {occurrence},
          topic = {topic}
        WHERE
          internal_id = {internalId}
      """
    ).on(
      'internalId -> internalId.get,
      'date -> date.toDate,
      'occurrence -> occurrence,
      'topic -> topic
    ).executeUpdate()
    this
  }


}

object Chat {
  /**
   * Parse a simple Chat from a ResultSet
   */
  val simple = {
    get[Pk[Long]]("chat.internal_id") ~
    get[Date]("chat.date") ~
    get[Int]("chat.occurrence") ~
    get[String]("chat.topic") map {
      case id~date~occurrence~topic =>
        Chat(id, new LocalDate(date.getTime), occurrence, topic, Seq(), Seq())
    }
  }

  /**
   * Parse a Chat from a ResultSet
   */
  val withEveryThing = {
    (simple ~
    (Item.simple ?) ~
    (Image.simple ?) * ) map { all => {
      all.map{case a~b~c => (a, b, c)}.groupBy(_._1).map {
        case (chat, xs) =>
          val (_, allItems, allImages) = xs.unzip3
          chat.copy(
            items = allItems
                     .filterNot(_ == None)
                      .map(_.get)
                        .groupBy(_.internalId)
                        .map{_._2.head}
                        .toList
                          .sortBy(_.timestamp.toDateTimeToday.toDate)
                          .toSeq,
            images = allImages
                      .filterNot(_ == None)
                      .map(_.get)
                        .groupBy(_.internalId)
                        .map{_._2.head}
                        .toList
                          .sortBy(_.timestamp.toDateTimeToday.toDate)
                          .toSeq
          )
        }
      }
    }
  }

  def load(chatid:Long) =
    DB.withConnection { implicit conn =>
      SQL("""
        SELECT
          *
        FROM
          chat
          left outer join item on chat.internal_id = item.chat_id
          left outer join image on chat.internal_id = image.chat_id
        WHERE
          chat.internal_id = {chatid}
        ORDER BY
          item.timestamp ASC,
          image.timestamp ASC
      """).on('chatid -> chatid).as(Chat.withEveryThing.map {_.headOption})
    }

  def all =
    DB.withConnection { implicit conn =>
      SQL("""
        SELECT
          *
        FROM
          chat
          left outer join item on chat.internal_id = item.chat_id
          left outer join image on chat.internal_id = image.chat_id
        ORDER BY
          chat.date ASC,
          item.timestamp ASC,
          image.timestamp ASC
      """).as(Chat.withEveryThing).toSeq
    }



  def occurrencesFor(date:LocalDate):Long =
    DB.withConnection { implicit conn =>
      SQL("""
        SELECT
          count(*) as c
        FROM
          chat
        WHERE
          date = {date}
      """)
      .on('date -> date.toDate)
      .as(scalar[Long].single)
    }


}