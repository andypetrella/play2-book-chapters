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



public class Dashboard extends Controller {
  static public Form<Data>  dashboardForm  = Form.form(Data.class);

  public static Result index() {
      return ok(
          views.html.dashboard.index.render(dashboardForm, Chat.find.all(), Chats.itemForm, Chats.imageForm)
      );
  }

  public static Result open() {
      Form<Data> dashboardForm = Form.form(Data.class);
      Form<Data> filledForm = dashboardForm.bindFromRequest();
      if(filledForm.hasErrors()) {
        return badRequest(
            views.html.dashboard.index.render(filledForm, Chat.find.all(), Chats.itemForm, Chats.imageForm)
        );
      } else {
        return ok(
            views.html.dashboard.index.render(filledForm, Chat.find.all(), Chats.itemForm, Chats.imageForm)
        );
      }
  }

  public static final class Data {
    public List<Long> chatIds = new ArrayList<Long>();

    public List<Chat> chats() {
      List<Chat> cs = new ArrayList<Chat>();
      for (Long l : chatIds) {
        Chat c = Chat.find.byId(l);
        if (c != null) {
          cs.add(c);
        }
      } // WOULD LOVE TO HAVE List.map here no ?
      return cs;
    }
  }

}