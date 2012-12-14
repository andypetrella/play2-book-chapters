package applicative

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

class LoginSpec extends Specification {
  "Login" should {

    "return an OK result" in {
      running(FakeApplication()) {
        // `getWrappedResult` because it's a Java app and
        // we're testing via Scala
        val result = controllers.Application.login().getWrappedResult

        status(result) must equalTo(OK)
      }
    }
    "have a template with an HTML form" in {
      running(FakeApplication()) {
        val html = views.html.login()

        contentType(html) must equalTo("text/html")
        contentAsString(html) must contain("<form")
      }
    }


    "that fails will redirect to the login page again" in {
      running(FakeApplication()) {
        val fakeRequest = new play.test.FakeRequest(POST, "/enter") //use the Java Test API!!
        val withEmail = fakeRequest.withFormUrlEncodedBody(Map("email" -> "unknown@example.com"))

        val result = play.test.Helpers.callAction(controllers.routes.ref.Application.enter(), withEmail)

        redirectLocation(result.getWrappedResult) must beSome.which(_ == "/login")
      }
    }
    "(using the router) that fails will redirect to the login page again" in {
      running(FakeApplication()) {
        val fakeRequest = new play.test.FakeRequest(POST, "/enter")
        val withEmail = fakeRequest.withFormUrlEncodedBody(Map("email" -> "unknown@example.com"))

        val result = play.test.Helpers.routeAndCall(withEmail)

        redirectLocation(result.getWrappedResult) must beSome.which(_ == "/login")
      }
    }


    "that succeeds will redirect to the dashboard page" in {
      running(FakeApplication()) {
        val address = new models.Address()
        address.fullStreet = "Smurf, 1"
        address.county = "Smurf Village"
        address.country = "SL"
        address.save

        val user = new models.User()
        user.name = "Grandpa"
        user.email = "grandpa@smurf.com"
        user.age = 109
        user.gender = false
        user.address = address
        user.save

        val fakeRequest = new play.test.FakeRequest(POST, "/enter")
        val withEmail = fakeRequest.withFormUrlEncodedBody(Map("email" -> "grandpa@smurf.com"))

        val result = play.test.Helpers.routeAndCall(withEmail)

        session(result.getWrappedResult) must not beNull;
        session(result.getWrappedResult).get("email") must beSome.which(_ == "grandpa@smurf.com")

        redirectLocation(result.getWrappedResult) must beSome.which(_ == "/dashboard")
      }
    }
  }
}