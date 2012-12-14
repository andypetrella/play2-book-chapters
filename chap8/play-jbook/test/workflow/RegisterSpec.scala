package workflow

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.libs.json.JsString
import play.api.libs.ws.WS
import org.fluentlenium.core.filter.FilterConstructor._

class RegisterSpec extends Specification {
  "Unknown user " should {
    "be able to register and then login" in {
      running(TestServer(3333, FakeApplication()), HTMLUNIT) { browser =>
        val baseURL = "http://localhost:3333"

        browser.goTo(baseURL+"/login")

        browser.$("a").click()
        browser.url must be_==(baseURL+"/form/user")
        browser.$("#gender_radio label").getTexts.get(0) must be_==("Female")

        val sauronEmail = "sauron@puppet.land"
        browser.fill("input", `with`("type").notContains("radio")).`with`(
            "Sauron",
            sauronEmail,
            150.toString,
            "Mordor, 0",
            "Middle-Earth"
          )
        browser.$("#gender_radio_false").click()
        browser.click("option", withText("Arda"));
        browser.submit(browser.$("form"))

        import java.util.concurrent.TimeUnit.SECONDS
        browser.await().atMost(1, SECONDS).until("input").withName("email").isPresent;

        browser.fill(browser.find("input")).`with`(sauronEmail)
        browser.submit(browser.$("form"))

        browser.await().atMost(1, SECONDS).until("#dashboard").isPresent;
        browser.url must be_==(baseURL+"/dashboard")
        browser.$("h1").getTexts.get(0) must be_==("Connected as " + sauronEmail)
      }
    }
  }
}