package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import play.api.Play.current
import play.api.db.DB
import anorm._
import models._

object Dashboard extends Controller {
  val dashboardForm  = Form[Data](
    mapping(
      "chatIds" -> seq(text)
    )(Data.apply)(Data.unapply)
  )

  def index = Action { implicit request =>
    Ok(views.html.dashboard.index(dashboardForm, Chat.all, Chats.itemForm, Chats.imageForm))
  }

  def open = Action { implicit request =>
    dashboardForm.bindFromRequest.fold (
        formWithErrors =>
          BadRequest(views.html.dashboard.index(formWithErrors, Chat.all, Chats.itemForm, Chats.imageForm)),
        { data => {
            Ok(views.html.dashboard.index(dashboardForm.fill(data), Chat.all, Chats.itemForm, Chats.imageForm))
        }
        }
    )
  }

  case class Data(chatIds:Seq[String]) {
    lazy val chats = DB.withConnection { implicit conn =>
      chatIds.map{ id =>
        Chat.load(id.toLong)
      }.collect {
        case Some(x) => x
      }
    }
  }

}