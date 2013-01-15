import play.api.Play.current
import play.api._
import play.api.mvc._

import play.api.db.DB
import anorm._

import org.joda.time.LocalDate;
import org.joda.time.LocalTime

import models._

object Global extends GlobalSettings {

  var address = Address(
        fullStreet = "Home, 2",
        county = "Lost Village",
        country = "BE"
    )

  var sampleUsers = adapt(Seq(
    User(
      name = "Me",
      email = Id("me@home.org"),
      age = 3,
      female = false,
      address = address
    ),
    User(
      name = "Robin",
      email = Id("thief@hood.com"),
      age = 26,
      female = true, //He wears PANTS!
      address = address
    ),
    User(
      name = "Who",
      email = Id("no@one.biz"),
      age = 2,
      female = false,
      address = address
    )
  ))

  def adapt(s:Seq[User]) = s.groupBy(_.email.get).map{e => (e._1, e._2.head)}

  val chats = Seq(
    Chat(
      date = LocalDate.now(),
      occurrence = 1,
      topic = "Talk, it's only talk",
      items = Seq(
        Item(
          user = sampleUsers("thief@hood.com"),
          timestamp = LocalTime.now(),
          message = "yeah"
        ),
        Item(
          user = sampleUsers("me@home.org"),
          timestamp = LocalTime.now(),
          message = "true"
        ),
        Item(
          user = sampleUsers("no@one.biz"),
          timestamp = LocalTime.now(),
          message = "indeed"
        )
      ),
      images = Seq()
    ),
    Chat(
      date = LocalDate.now(),
      occurrence = 1,
      topic = "News...",
      items = Seq(
        Item(
          user = sampleUsers("no@one.biz"),
          timestamp = LocalTime.now(),
          message = "new HashMap()"
        ),
        Item(
          user = sampleUsers("me@home.org"),
          timestamp = LocalTime.now(),
          message = "new Date()"
        ),
        Item(
          user = sampleUsers("thief@hood.com"),
          timestamp = LocalTime.now(),
          message = "Men..."
        )
      ),
      images = Seq()
    )
  )

  override def onStart(app:Application) = {
    DB.withTransaction { implicit conn =>
      if (User.all.isEmpty) {
        address = address.save

        sampleUsers = adapt(for {
          u <- sampleUsers.values.toSeq
        } yield u.copy(address = address).save)

        for {
          c <- chats;
          val chat = c.save;
          item <- chat.items;
          val it = item.save(chat);
          image <- chat.images;
          val im = image.save(chat)
        } yield ()
      }
    }
  }


}