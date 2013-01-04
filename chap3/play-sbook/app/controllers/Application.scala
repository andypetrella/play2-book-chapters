package controllers

import play.api._
import play.api.mvc._

import models.Item
import org.joda.time.LocalTime

object Application extends Controller {
  
  def index = Action {
    val level = 1
    val items = Seq(
        Item("Turgidson", LocalTime.now(), "Uh, we're... Still trying to figure out the meaning of the las phrase, sir."),
        Item("Muffley", LocalTime.now(), "There's nothing to figure out, General Turgidson. This man is obviously a psychotic"),
        Item("Turgidson", LocalTime.now(), "We-he-ell, I'd like to hold off judgment on a thing like that, sir, until all the facts are in.")
    )
    Ok(views.html.index("It Works!")(level, items))
  }
  
}