package controllers

import play.api._
import play.api.Play.current
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._

import org.joda.time.LocalTime
import org.joda.time.LocalDate

import play.api.db.DB
import anorm.{Pk, Id, NotAssigned}

import models._

object Chats extends Controller {

  def optToPk[T](om:Mapping[Option[T]]) =
    om.transform[Pk[T]](o => o.map{x=>Id(x)}.getOrElse(NotAssigned), (_:Pk[T]).toOption)

  val chatForm =
    Form[Chat](
      mapping(
        "internalId" -> optToPk(optional(longNumber)),
        "date" -> date.transform(date => new LocalDate(date), (l:LocalDate) => l.toDate),
        "occurrence" -> number,
        "topic" -> nonEmptyText,
        "items" -> ignored(Seq():Seq[Item]),
        "images" -> ignored(Seq():Seq[Image])
      )(Chat.apply)(Chat.unapply)
    )
  val itemForm =
    Form[Item](
      mapping(
        "internalId" -> optToPk(optional(longNumber)),
        "user" -> ignored(null:User),
        "timestamp" -> text.transform(date => new LocalTime(date), (l:LocalTime) => l.toString),
        "message" -> text
      )(Item.apply)(Item.unapply)
    )
  val imageForm =
    Form[Image](
      mapping(
        "internalId" -> optToPk(optional(longNumber)),
        "user" -> ignored(null:User),
        "timestamp" -> text.transform(date => new LocalTime(date), (l:LocalTime) => l.toString),
        "caption" -> text,
        "filePath" -> ignored("")
      )(Image.apply)(Image.unapply)
    )

  def registerChat = Action { implicit request =>
      val m = Map("occurrence" -> (Chat.occurrencesFor(LocalDate.now())+1).toString);
      Ok(views.html.chat(chatForm.bind(m)));
  }


  def loadChat = Action { implicit request =>
    (for {
      chatid <- request.queryString("chatid").headOption;
      chat <- Chat.load(chatid.toLong)
    } yield Ok(views.html.chatroom(chat, itemForm, imageForm))).
    getOrElse {
      BadRequest("Missing params or nothing found...")
    }
  }

  def createChat() = Action { implicit request =>
    chatForm.bindFromRequest.fold (
      formWithErrors => BadRequest(formWithErrors.errorsAsJson),
      chat => {
        DB.withTransaction { implicit conn =>
          chat.save
          Redirect( routes.Chats.allChats() )
        }
      }
    )
  }

  def allChats = Action { implicit request =>
    Ok(views.html.chats(Chat.all))
  }

  def talk(chat:Long) = Action { implicit request =>
    (for {
      e <- request.session.get("email");
      user <- User.load(e);
      chat <- Chat.load(chat)
    } yield {
      itemForm.bindFromRequest.fold(
        f => BadRequest(f.errorsAsJson.toString),
        i => {
          val item = i.copy(user = user)
          item.save(chat.copy(items = item +: chat.items))
          Ok(views.html.chatroom(Chat.load(chat.internalId.get).get, itemForm, imageForm))
        }
      )
    }).getOrElse(BadRequest("not found..."))
  }


  def receiveImage(chat:Long) = Action { implicit request =>
    (for {
      e <- request.session.get("email");
      user        <- User.load(e);
      chat        <- Chat.load(chat);
      body        <- request.body.asMultipartFormData;
      pic         <- body.file("pic");
      contentType <- pic.contentType
    } yield {
      imageForm.bindFromRequest.fold(
        f => BadRequest(f.errorsAsJson.toString),
        i => {
          Image.ImageType.find(_ == contentType) map { _ =>
            val image = i.copy(user = user, filePath = pic.ref.file.getPath) //ref is a TemporaryFile which has a convenient method moveTO
            image.save(chat.copy(images = image +: chat.images));
            Ok(views.html.chatroom(Chat.load(chat.internalId.get).get, itemForm, imageForm))
          } getOrElse(BadRequest("wrong mime : "  + pic.contentType))
        }
      )
    }).getOrElse(BadRequest("not found..."))
  }

}