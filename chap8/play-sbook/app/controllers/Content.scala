package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._

import models._

object Content extends Controller {

  def getImage(id:Long) = Action { implicit request =>
    Image.load(id).map { image =>
      Ok.sendFile(image.pic)
    }.getOrElse(BadRequest("Bad File..."))

  }

  def atom(emailsString:String) = Action { implicit request =>
    val (emails, users) =
      emailsString.split("/")
                  .map(User.load)
                  .filter(_.isDefined)
                  .map {
                    case Some(u) => (u.email.get, u)
                  }
                  .unzip

    val chats = Chat.all.foldLeft(Map():Map[User, Seq[Chat]]) { (acc, chat) =>
      chat
        .items
        .map(_.user)
          .filter(emails contains _.email.get)
            .foldLeft(acc) { (m, u) =>
              val csOpt = m.get(u)
                            .map{ cs =>
                              cs
                                .find(_ == chat)
                                .map{_ => cs}
                                .getOrElse{chat +: cs}
                            }
                            .getOrElse(Seq(chat))

              m + ( u -> csOpt)
            }
    }

    Ok(views.xml.content.atom(chats, users)).as("application/atom+xml");

  }

}