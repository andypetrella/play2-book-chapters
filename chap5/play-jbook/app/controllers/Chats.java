package controllers;

import play.*;
import play.mvc.*;
import static play.mvc.Results.*;
import play.data.*;
import play.libs.*;
import models.*;
import play.data.validation.*;
import static play.data.validation.Constraints.*;
import javax.validation.*;
import views.html.*;
import java.util.*;

import org.joda.time.LocalDate;

public class Chats extends Controller {
  static public Form<Chat>  chatForm  = form(Chat.class);

  public static Result registerChat() {
      Map<String, String> m = new HashMap<String, String>();
      int nextOccurrence =
        Chat.occurrencesFor(LocalDate.now())+1;
      m.put("occurrence", ""+nextOccurrence);
      return ok(views.html.chat.render(chatForm.bind(m)));
  }

  public static Result loadChat() {
    Map<String,String[]> queryString = request().queryString();
    Long chatId = Long.parseLong(queryString.get("chatid")[0]);

    Chat chat = Chat.find
                      .where()
                        .eq("internalId", chatId)
                        .join("items")
                          .join("items.user")
                        .join("images")
                          .join("images.user")
                      .findUnique();

    return ok(
      views.html.chatroom.render(chat, itemForm, imageForm)
    );
  }

  public static Result allChats() {
      return ok(
          views.html.chats.render(Chat.find.all())
      );
  }

  public static Result createChat() {
      Form<Chat> boundForm = chatForm.bindFromRequest();
      if (boundForm.hasErrors()) {
          return badRequest("Cannot create chat instance : "
            + boundForm.errorsAsJson().toString());
      } else {
          Chat chat = boundForm.get();

          chat.save();
          return redirect( routes.Chats.allChats() );
      }
  }

  static public Form<Item>  itemForm  = form(Item.class);
  public static Result talk(Long chatId) {
    User user = User.find.byId(session("email"));
    Chat chat = Chat.find
                      .where()
                        .eq("internalId", chatId)
                      .join("items")
                    .findUnique();

    Form<Item> boundForm = itemForm.bindFromRequest();
    Item item = itemForm.bindFromRequest().get();
    item.user = user;
    chat.items.add(item);

    chat.save();

    return ok(
        views.html.chatroom.render(chat, itemForm, imageForm)
    );
  }


  public static Form<Image> imageForm = form(Image.class);
  public static Result receiveImage(Long chatId) {
    User user = User.find.byId(session("email"));
    Chat chat = Chat.find
                      .where()
                        .eq("internalId", chatId)
                      .join("items")
                    .findUnique();

    //  GET SOME DATA FROM THEN URL FORM ENCODED DATA
    Form<Image> filledForm = imageForm.bindFromRequest();
    if(filledForm.hasErrors()) {
      return badRequest(
          filledForm.errors().toString()
      );
    } else {
      Http.MultipartFormData body;
      //  RECOVER THE WHOLE BODY AS MULTIPART
      body = request().body().asMultipartFormData();

      //  THE PLAY2 API PROVIDES A WAY TO GET THE FILE
      //    +> SO EASILY !!!
      Http.MultipartFormData.FilePart pic = body.getFile("pic");
      //  CHECK THE IMAGE TYPE IS VALID -- part of the enum
      if(Image.ImageType.get(pic.getContentType()) == null) {
        return badRequest(
          views.html.chatroom.render(chat, itemForm, imageForm)
        );
      }

      Image image = filledForm.get();

      image.pic = pic.getFile();
      image.filePath = pic.getFile().getPath();
      image.user = user;
      chat.images.add(image);
      chat.save();

      return ok(
          views.html.chatroom.render(chat, itemForm, imageForm)
      );
    }
  }


}