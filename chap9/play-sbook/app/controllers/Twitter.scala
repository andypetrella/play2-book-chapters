package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._

import play.api.libs.json._
import play.api.libs.ws.WS

import play.api.libs.concurrent.Execution.Implicits._

object Twitter extends Controller {
  val SEARCHURL = "http://search.twitter.com/search.json"

  def searchTag(tag:String) = Action {
    findAndSeek("#" + tag, true)
  }

  def mentioning(user:String) = Action {
    findAndSeek("@" + user, false)
  }

  def findAndSeek(q:String, isTag:Boolean) = Async /*NON BLOCKING */ {
    val promise = WS.url(SEARCHURL).withQueryString("q" -> q).get();
    val promisedResult =promise.map{ resp => {
        val json = resp.json

        val results = (json\ "results").as[Seq[JsValue]];

        val tweets = JsArray(results.map{ v =>
          JsObject(Seq(
            "user"  -> (v \ "from_user"),
            "tweet" -> (v \ "text")
          ))
        })

        Ok(tweets);
      }
    }

    // BLOCKING
    //promisedResult.await.get


    promisedResult// BLOCKING .await.get
  }


}