package applicative

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

import play.libs.Json //the Java Json lib

import models._
import controllers._

class RegisterSpec extends Specification {
  "Register " should {
    "a new user with valid data permit a login" in {
      running(FakeApplication()) {
        //create the Smurfette address
        val address = new models.Address()
        address.fullStreet = "Smurf, 2"
        address.county = "Smurf Village"
        address.country = "SL"
        // create the Smurgette (feeling like Gargamel now...)
        val user = new models.User()
        user.name = "Smurfette"
        user.email = "smurfette@smurf.com"
        user.age = 100
        user.female = true
        user.address = address

        //take the user and bind it to the user form in Users.java
        //WARN :> Using the **Java** version of Json!!! <:
        val userForm = Users.userForm.bind(Json.toJson(user))
        //retrieve the form data as a Map
        val userData = userForm.data()

        //create user using the form data
        val fakeRequest = new play.test.FakeRequest(POST, "/user")
        //fill the request body with the Map of user information
        val withData = fakeRequest.withFormUrlEncodedBody(userData)
        val createResult = play.test.Helpers.routeAndCall(withData)
        redirectLocation(createResult.getWrappedResult) must beSome.which(_ == "/login")

        //enter the site using create user
        val fakeRequest2 = new play.test.FakeRequest(POST, "/enter")
        val withEmail = fakeRequest2.withFormUrlEncodedBody(Map("email" -> "smurfette@smurf.com"))
        val result = play.test.Helpers.routeAndCall(withEmail)
        session(result.getWrappedResult) must not beNull;
        session(result.getWrappedResult).get("email") must beSome.which(_ == "smurfette@smurf.com")
      }
    }
  }
}