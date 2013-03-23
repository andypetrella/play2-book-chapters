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


public class Users extends Controller {
  static public Form<User> userForm = Form.form(User.class);

  public static Result registerUser() {
    return ok(user.render(userForm));
  }

  @play.db.ebean.Transactional
  public static Result createUser() {
    Form<User> boundForm = userForm.bindFromRequest();
    if (boundForm.hasErrors()) {
      return badRequest(user.render(boundForm));
    } else {
      User user = boundForm.get();
      Address existingOne =
        Address.find
            .where()
                .eq("fullStreet", user.address.fullStreet)
                .eq("county", user.address.county)
                .eq("country", user.address.country)
            .findUnique();
      if (existingOne != null) {
        user.address = existingOne;
      }
      user.save();
      return redirect(routes.Application.login());
    }
  }


  public static Result allUsers() {
      //necessary in order to fetch the whole address at once for each user
      //Otherwise it will be proxied and its field won't be available in the templates.
      List<User> users = User.find.fetch("address").findList();
      return ok(
          views.html.users.render(users)
      );
  }

}