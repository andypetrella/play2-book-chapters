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
import java.io.*;

import org.w3c.dom.*;

public class Content extends Controller {

  public static Result getImage(Long imageId) {
    Image image = Image.find.byId(imageId);
    try {
      return ok(new FileInputStream(image.pic()));
    } catch (FileNotFoundException f) {
      return badRequest("Bad File...");
    }
  }

  public static Result atom(String userEmails) {
      Map<User, Set<Chat>> chats = new HashMap<User, Set<Chat>>();

      List<String> emails = new ArrayList<String>();
      for (String e : userEmails.split("/")) {
        emails.add(e);
      }

      List<User> users = User.find
                                .fetch("address")
                                .where()
                                .in("email", emails)
                              .findList();

      List<Chat> listOfChats =  Chat.find
                                      .fetch("items")
                                      .fetch("items.user")
                                      .where()
                                        .in("items.user.email", emails)
                                      .orderBy("items.user.email")
                                    .findList();

      for (Chat chat : listOfChats) {
        for (Item i : chat.items) {
          Set<Chat> list = chats.get(i.user);
          if (list == null) {
            list = new HashSet<Chat>();
            chats.put(i.user, list);
          }
          list.add(chat);
        }
      }
      return ok(views.xml.content.atom.render(chats, users)).as("application/atom+xml");
    }

  // @BodyParser.Of(BodyParser.Xml.class)
  // public static Result content() {
  //   Document doc = request().body().asXml()

  //   //DEAL WITH DOCUMENT

  //   return ok(
  //       //...
  //   );
  // }

}