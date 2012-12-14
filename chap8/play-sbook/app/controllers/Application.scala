package controllers

import play.api._
import play.api.mvc._

import models.Item
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.LocalTime

import models.User
import models.Item
import models.Chat

object Application extends Controller {

  /**
   *  JavaScript version of the router to be injected in the main page
   */
  def js = Action {implicit request =>
    Ok(
      Routes.javascriptRouter("playRouter")(
        // Routes
        routes.javascript.Chats.receiveImage,
        controllers.routes.javascript.Chats.chatsStream,

        controllers.routes.javascript.Twitter.mentioning,
        controllers.routes.javascript.Twitter.searchTag
      )
    ).as("text/javascript")
  }


  def index = Action { implicit request =>
    val email = request.session.get("email");
    email.map { _ =>
      Redirect( routes.Dashboard.index() )
    } getOrElse {
      Unauthorized(views.html.login())
    }
  }

  def login = Action { implicit request =>
    Ok(views.html.login())
  }

  def logout = Action { implicit request =>
    Ok(
        views.html.login()
    ).withNewSession
  }

  def enter = Action { implicit request =>
    (for {
      f <- request.body.asFormUrlEncoded;
      emails <- f.get("email");
      email <- emails.headOption;
      user <- User.load(email)
    } yield
      Redirect( routes.Dashboard.index() ).withSession(
        session + ("email" -> email)
      )
    ).getOrElse {
      Redirect(routes.Application.login())
    }
  }

}