package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import java.util.*;
import static java.util.Arrays.asList;

import static scala.collection.JavaConversions.collectionAsScalaIterable;

import sample.SampleUsers;

import models.*;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Days;

public class Application extends Controller {

  /**
   *  JavaScript version of the router to be injected in the main page
   */
  public static Result js() {
    response().setContentType("text/javascript");
    return ok(
      Routes.javascriptRouter("playRouter",
        // Routes
        controllers.routes.javascript.Chats.receiveImage(),
        controllers.routes.javascript.Chats.chatsStream(),
        controllers.routes.javascript.Real.so_web(),

        controllers.routes.javascript.Twitter.mentioning(),
        controllers.routes.javascript.Twitter.searchTag()
      )
    );
  }

  public static Result index() {
    String email = session().get("email");
    if (email != null) {
      return redirect( routes.Dashboard.index() );
    } else {
      return unauthorized(
        views.html.login.render()
      );
    }
  }

  public static Result login() {
    return ok(
        views.html.login.render()
    );
  }

  public static Result logout() {
    session().clear();
    return ok(
        views.html.login.render()
    );
  }

  public static Result enter() {
    Map<String,String[]> params;
    params = request().body().asFormUrlEncoded();

    String email = params.get("email")[0];
    User user = User.find.byId(email);
    if (user == null) {
      return redirect( routes.Application.login() );
    } else {
      session("email", email);
      return redirect( routes.Dashboard.index() );
    }
  }


}

