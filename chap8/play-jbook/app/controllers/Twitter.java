package controllers;

import play.*;
import play.mvc.*;
import static play.mvc.Results.*;
import play.data.*;
import play.libs.*;
import com.avaje.ebean.*;
import play.db.ebean.*;
import models.*;
import play.data.validation.*;
import static play.data.validation.Constraints.*;
import javax.validation.*;
import views.html.*;
import java.util.*;
import play.cache.*;


import static play.libs.F.*;
import play.libs.Json;
import org.codehaus.jackson.*;
import org.codehaus.jackson.node.*;

public class Twitter extends Controller {

  private static final String SEARCHURL = "http://search.twitter.com/search.json";

  public static Result searchTag(String tag) {
    String q = "#" + tag;
    return findAndSeek(q, true);
  }

  public static Result mentioning(String user) {
    String q = "@" + user;
    return findAndSeek(q, false);
  }

  private static Result findAndSeek(String q, Boolean isTag) {
    // Initialize the search that will get the Twitter Json response
    //  The response is not yet there... we've only got a PROMISE of it
    Promise<WS.Response> promise = WS.url(SEARCHURL).setQueryParameter("q", q).get();

    //adapt the Twitter Json structure into a custom one, usable in our UI
    Promise<Result> promisedResult = promise.map(
      new Function<WS.Response, Result>() {
        public Result apply(WS.Response response) {
          // the original Twitter response is Json encoded
          JsonNode json = response.asJson();
          // we're only interested in the results property
          ArrayNode results = (ArrayNode) json.get("results");

          // Our representation container
          List<Map<String, String>> tweets = new ArrayList<Map<String, String>>();
          Iterator<JsonNode> it = results.iterator();
          //loop (argh) on results
          while (it.hasNext()) {
            JsonNode t = it.next();
            Map<String, String> m = new HashMap<String, String>();
            // retrieve the user name
            m.put("user", t.get("from_user").asText());
            //retrieve the text
            m.put("tweet", t.get("text").asText());
            //save the new object
            tweets.add(m);
          }
          //return an result with OK status
          // containing the tweets as Json in its body
          return ok(Json.toJson(tweets));
        }
      }
    );


    // ask the promise's result using the get method

    //BLOCKING =>
    //return promisedResult .get();

    //   NON BLOCKING
    return async(promisedResult);
  }

}
