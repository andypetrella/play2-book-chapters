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
import java.util.concurrent.TimeUnit;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;
import org.codehaus.jackson.JsonNode;

import akka.util.*;


import static play.libs.F.*;

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
      views.html.chatroom.render(chat)
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
          return redirect( routes.Dashboard.index() );
      }
  }

  static final public Form<Item>  itemForm  = form(Item.class);

  public static WebSocket<JsonNode> chatsStream(final String chatIds, final Long timestamp) {
    final User user = User.find.byId(session("email"));
    final List<Long> chatIdsList = new ArrayList<Long>();

    String[] cis = chatIds.split(",");
    for (String c : cis) {
      chatIdsList.add(Long.parseLong(c));
    }

    return new WebSocket<JsonNode>() {

      // Called when the WebSocket Handshake is done.
      public void onReady(WebSocket.In<JsonNode> in, final WebSocket.Out<JsonNode> out) {

        // For each event received on the socket,
        in.onMessage(new Callback<JsonNode>() {
           public void invoke(JsonNode event) {
            Long chatId = event.get("chatId").getLongValue();
            Chat chat = Chat.find.byId(chatId);

            Form<Item> f = itemForm.bind(event);

            if (f.hasErrors()) {
              ObjectNode error = Json.newObject();
              error.put("status", "error");
              out.write(error);

            } else {
              Item item = f.get();
              item.user = user;
              chat.items.add(item);
              chat.save();

              ObjectNode result = Json.newObject();
              result.put("status", "success");
            }
          }
        });

        //scheduling part that fetches the last updates
        Akka.system().scheduler().schedule(
              Duration.create(0, TimeUnit.MILLISECONDS),
              Duration.create(1, TimeUnit.SECONDS),
            new Runnable() {

              //cache the last update date
              Long lastTimestamp = timestamp;

              public void run() {

                //fetch the content since lastTimestamp
                LocalTime t = new LocalTime(lastTimestamp);
                //holds the potential data sent to the client
                ObjectNode result = Json.newObject();
                //tell the client to update something
                result.put("update", "true");

                //control the number of message sent to the client
                boolean send = false;

                //check what have been updated for each chat
                // and create the message part of it
                for (Long chatId : chatIdsList) {
                  ObjectNode current = checkChat(chatId, t);
                  if (current != null) {
                    //at least one event available
                    send = true;
                    result.put("chat"+chatId, current);
                  }
                }
                //if something has been set
                if (send) {
                  //send == write to the output
                  out.write(result);
                }

                //update the last checked date || not always good but OK...
                lastTimestamp = System.currentTimeMillis();
              }

              public ObjectNode checkChat(Long chatId, LocalTime t) {
                //holds the changes for the current chat
                  ObjectNode current = Json.newObject();

                  //fetch the chat
                  Chat chat = Chat.find.byId(chatId);

                  List<Item> items = chat.items;
                  List<Image> images = chat.images;
                  //thanks to Java...
                  List<Item> ritems = new ArrayList<Item>();
                  List<Image> rimages = new ArrayList<Image>();
                  //that's a map ...
                  for (Item i : items) {
                    if (i.timestamp().isAfter(t)) {
                      ritems.add(i);
                    }
                  }
                  //that's the exact map... on another instance
                  for (Image i : images) {
                    if (i.timestamp().isAfter(t)) {
                      rimages.add(i);
                    }
                  }

                  //build a message part only when necessary
                  if (ritems.size() > 0 || rimages.size() > 0) {
                    //put the data
                    current.put("items", Json.toJson(ritems));
                    current.put("images", Json.toJson(rimages));
                    return current;
                  } else {
                    //no events to send
                    return null;
                  }
              }
            }
        );
      }

    };
  }



  public static Form<Image> imageForm = form(Image.class);

  public static Result receiveImage(Long chatId) {
    User user = User.find.byId(session("email"));
    Chat chat = Chat.find .where() .eq("internalId", chatId) .join("items") .findUnique();
    Form<Image> filledForm = imageForm.bindFromRequest();
    if(filledForm.hasErrors()) {
      return badRequest(filledForm.errors().toString());
    } else {
      Http.MultipartFormData body;
      body = request().body().asMultipartFormData();
      Http.MultipartFormData.FilePart pic = body.getFile("pic");
      if(Image.ImageType.get(pic.getContentType()) == null) {
        return badRequest("bad file : " + pic.getContentType() );
      }

      Image image = filledForm.get();

      image.pic = pic.getFile();
      image.filePath = pic.getFile().getPath();
      image.user = user;
      chat.images.add(image);
      chat.save();

      return ok("image saved");
    }
  }


}