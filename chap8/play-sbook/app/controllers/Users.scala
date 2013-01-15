package controllers

import play.api._
import play.api.Play.current
import play.api.mvc._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

import play.api.db.DB
import anorm._

import play.api.data._

import models._

object Users extends Controller {

  def optToPk[T](om:Mapping[Option[T]]) =
    om.transform[Pk[T]](o => o.map{x=>Id(x)}.getOrElse(NotAssigned), (_:Pk[T]).toOption)

  lazy val userMapping:Mapping[User] =
    mapping(
      "name" -> nonEmptyText,
      "email" -> optToPk(optional(email)),
      "age" -> number(min=0, max=150),
      "female" -> boolean,
      "address" -> addressMapping,
      "spouse" -> ignored(None:Option[User]),
      "friends" -> ignored(Seq():Seq[User])
    )(User.apply)(User.unapply)

  lazy val addressMapping =
    mapping(
      "internalId" -> optToPk(optional(longNumber)),
      "fullStreet" ->
        (
          text verifying pattern("""[A-Z].*,[0-9]""".r,
          error="Street starts with upper case and ends with comma and number")
        ),
      "county" -> nonEmptyText,
      "country" -> nonEmptyText
    )(Address.apply)(Address.unapply)

  val userForm = Form[User](userMapping)



  def registerUser = Action { implicit request =>
    Ok(views.html.user(userForm))
  }

  def createUser = Action { implicit request =>
    userForm.bindFromRequest.fold (
      formWithErrors => BadRequest(views.html.user(formWithErrors)),
      user => {
        DB.withTransaction { implicit conn =>
          val address = user.address.save
          val u = user.copy(address = address).save
          Redirect( routes.Application.login() )
        }
      }
    )
  }

  def allUsers = Action { implicit request =>
    Ok(views.html.users(User.all))
  }

}