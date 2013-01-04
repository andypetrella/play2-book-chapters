package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import java.util.List;
import static java.util.Arrays.asList;

import static scala.collection.JavaConversions.collectionAsScalaIterable;

import models.Chat;
import models.Item;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.Days;

public class Application extends Controller {
  
  public static Result index() {
    DateTime today = DateTime.now();
    DateTime yesterday = today.minus(Days.ONE);

    Chat chat11 = new Chat(yesterday, 1, asList(
        new Item("me",    LocalTime.now(), "Hello!"),
        new Item("other", LocalTime.now(), "Hi!"),
        new Item("me",    LocalTime.now(), "Fine?"),
        new Item("other", LocalTime.now(), "Yes")
    ));
    //later on
    Chat chat12 = new Chat(yesterday, 2, asList(
        new Item("me",    LocalTime.now(), "It's hot today!"),
        new Item("other", LocalTime.now(), "Indeed...")
    ));

    Chat chat21 = new Chat(today, 1, asList(
        new Item("me",    LocalTime.now(), "Hello!"),
        new Item("me",    LocalTime.now(), "Youhou?"),
        new Item("no-one", LocalTime.now(), "...")
    ));
    Chat chat22 = new Chat(today, 2, asList(
        new Item("me",    LocalTime.now(), "Ding ding!"),
        new Item("me",    LocalTime.now(), "Poueeeeeeeeeet?"),
        new Item("no-one", LocalTime.now(), "...")
    ));

    Chat chat23 = new Chat(today, 3, asList(
        new Item("me",    LocalTime.now(), "No one?"),
        new Item("no-one", LocalTime.now(), "Yes?")
    ));

    return ok(index.render("It Works!", asList(
            chat23,
            chat11, 
            chat21, 
            chat12, 
            chat22
        ))
    );
  }

}

