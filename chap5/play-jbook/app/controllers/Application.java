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

  public static Result index() {
    String email = session().get("email");
    if (email != null) {
      return redirect( routes.Chats.allChats() );
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
      return redirect( routes.Chats.allChats() );
    }
  }













  public static LocalDate today = LocalDate.now();
  public static LocalDate yesterday = today.minus(Days.ONE);

  public static Chat chat11 = new Chat(yesterday, 1, asList(
      new Item(SampleUsers.get.me,    LocalTime.now(), "Hello!"),
      new Item(SampleUsers.get.robin, LocalTime.now(), "Hi!"),
      new Item(SampleUsers.get.me,    LocalTime.now(), "Fine?"),
      new Item(SampleUsers.get.robin, LocalTime.now(), "Yes")
  ), new ArrayList<Image>());
  //later on
  public static Chat chat12 = new Chat(yesterday, 2, asList(
      new Item(SampleUsers.get.me,    LocalTime.now(), "It's hot today!"),
      new Item(SampleUsers.get.robin, LocalTime.now(), "Indeed...")
  ), new ArrayList<Image>());

  public static Chat chat21 = new Chat(today, 1, asList(
      new Item(SampleUsers.get.me,    LocalTime.now(), "Hello!"),
      new Item(SampleUsers.get.me,    LocalTime.now(), "Youhou?"),
      new Item(SampleUsers.get.noOne, LocalTime.now(), "...")
  ), new ArrayList<Image>());
  public static Chat chat22 = new Chat(today, 2, asList(
      new Item(SampleUsers.get.me,    LocalTime.now(), "Ding ding!"),
      new Item(SampleUsers.get.me,    LocalTime.now(), "Poueeeeeeeeeet?"),
      new Item(SampleUsers.get.noOne, LocalTime.now(), "...")
  ), new ArrayList<Image>());

  public static Chat chat23 = new Chat(today, 3, asList(
      new Item(SampleUsers.get.me,    LocalTime.now(), "No one?"),
      new Item(SampleUsers.get.noOne, LocalTime.now(), "Yes?")
  ), new ArrayList<Image>());

  public static Result sample() {

    return ok(sample.render("Chat Archives", asList(
        chat23,
        chat11,
        chat21,
        chat12,
        chat22
      ))
    );
  }

}

