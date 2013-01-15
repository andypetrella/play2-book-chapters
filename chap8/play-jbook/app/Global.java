import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

import models.*;
import sample.*;

import org.joda.time.LocalTime;
import org.joda.time.LocalDate;

public class Global extends GlobalSettings {
  static {
    //register quick formatter for date in Chat !
    play.data.format.Formatters.register(
      LocalDate.class,
      new play.data.format.Formatters.SimpleFormatter<LocalDate>() {

        @Override
        public LocalDate parse(String input, Locale l) throws java.text.ParseException {
          return LocalDate.parse(input);
        }

        @Override
        public String print(LocalDate localDate, Locale l) {
          return localDate.toString();
        }

    });
    play.data.format.Formatters.register(
      LocalTime.class,
      new play.data.format.Formatters.SimpleFormatter<LocalTime>() {

        @Override
        public LocalTime parse(String input, Locale l) throws java.text.ParseException {
          return LocalTime.parse(input);
        }

        @Override
        public String print(LocalTime localTime, Locale l) {
          return localTime.toString();
        }

    });
  }


  public void onStart(Application app) {
    if (User.find.byId("me@home.org") == null) {
      //System.out.println("Inserting users => " + "email : me@home.org, thief@hood.com");
      Map userData = new HashMap();
      userData.put("name", "Me");
      userData.put("email", "me@home.org");
      userData.put("age", "3");
      userData.put("female", "false");
      userData.put("address.fullStreet", "Home, 2");
      userData.put("address.county", "Lost Village");
      userData.put("address.country", "BE");
      SampleUsers.get.me = controllers.Users.userForm.bind(userData).get();
      SampleUsers.get.me.save();
      //System.out.println("User inserted");

      Address address = SampleUsers.get.me.address;


      SampleUsers.get.robin = new User();
      SampleUsers.get.robin.name = "Robin";
      SampleUsers.get.robin.email = "thief@hood.com";
      SampleUsers.get.robin.age = 26;
      SampleUsers.get.robin.female = true; //He wears PANTS!
      SampleUsers.get.robin.address = address; //same address as me...

      SampleUsers.get.robin.save();
      //System.out.println("Robin inserted");


      SampleUsers.get.noOne = new User();
      SampleUsers.get.noOne.name = "Who";
      SampleUsers.get.noOne.email = "no@one.biz";
      SampleUsers.get.noOne.age = 2;
      SampleUsers.get.noOne.female = false;
      SampleUsers.get.noOne.address = address; //same address as me...

      SampleUsers.get.noOne.save();
      //System.out.println("No One inserted");

      Chat chat = new Chat();
      chat.topic = "Talk, it's only talk";
      chat.date = LocalDate.now();
      chat.occurrence = 1;
      chat.save();
      Item item = new Item();
      item.timestamp = LocalTime.now();
      item.message = "yeah";
      item.user = SampleUsers.get.robin;
      chat.items.add(item);
      chat.save();
      item = new Item();
      item.timestamp = LocalTime.now();
      item.message = "true";
      item.user = SampleUsers.get.me;
      chat.items.add(item);
      chat.save();
      item = new Item();
      item.timestamp = LocalTime.now();
      item.message = "indeed";
      item.user = SampleUsers.get.robin;
      chat.items.add(item);
      chat.save();

      chat = new Chat();
      chat.topic = "News...";
      chat.date = LocalDate.now();
      chat.occurrence = 1;
      chat.save();
      item = new Item();
      item.timestamp = LocalTime.now();
      item.message = "new HashMap()";
      item.user = SampleUsers.get.noOne;
      chat.items.add(item);
      chat.save();
      item = new Item();
      item.timestamp = LocalTime.now();
      item.message = "new Date()";
      item.user = SampleUsers.get.me;
      chat.items.add(item);
      chat.save();
      item = new Item();
      item.timestamp = LocalTime.now();
      item.message = "Men...";
      item.user = SampleUsers.get.robin;
      chat.items.add(item);
      chat.save();
    } else {
      List<User> users = User.find.join("address").findList();
      for (User user : users) {
        if (user.email.equals("me@home.org")) {
          SampleUsers.get.me = user;
        } else  if (user.email.equals("thief@hood.com")) {
          SampleUsers.get.robin = user;
        } else if (user.email.equals("no@one.biz")) {
          SampleUsers.get.noOne = user;
        }
      }

      //System.out.println("Users available => " + "emails : me@home.org, thief@hood.com and no@one.biz");
    }
  }
}
