package workflow

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import play.api.libs.json.JsString
import play.api.libs.ws.WS

class TwitterSpec extends Specification {
  "Twitter search service " should {
    "return tweets with the user name when searching for mentions" in {
      // NOW we're running a server!
      running(TestServer(3333, FakeApplication())) {
        //we use our application through a real HTTP request.
        val response = await(WS.url("http://localhost:3333/ws/tw/mentions/noootsab").get)
        // we assert that the response must be ok
        response.status must equalTo(OK)
        // we parse the response into as json
        // then we retrieve all "tweet" properties in this converted response
        // which tweet texts (an array) must all respect a certain pattern
        (response.json \\ "tweet") must haveAllElementsLike {
          // the pattern is checking the String type
          //  this string must itself matching a regex
          case s:JsString => s.toString must beMatching(".*noootsab.*")
          // if not a String => the test must fail
          case x => ko("'tweet' must be a String : " + x)
        }
      }
    }
  }
}