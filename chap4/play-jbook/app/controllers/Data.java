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


public class Data extends Controller {

    static Form<User> userForm = Form.form(User.class);

    public static Result show() {
        return ok(data.render(userForm));
    }

    @play.db.ebean.Transactional                                        /*1*/
    public static Result post() {
        Form<User> boundForm = userForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(data.render(boundForm));
        } else {
            User user = boundForm.get();
            Address existingOne =                                       /*2*/
                Address.find
                    .where()
                        .eq("fullStreet", user.address.fullStreet)
                        .eq("county", user.address.county)
                        .eq("country", user.address.country)
                    .findUnique();
            if (existingOne != null) {                                  /*3*/
                user.address = existingOne;
            }
            user.save();                                                /*4*/
            return ok(data.render(boundForm));
        }
    }

    public static Result allUsers() {
        //necessary in order to fetch the whole address at once for each user
        //Otherwise it will be proxied and its field won't be available in the templates.
        List<User> users = User.find.fetch("address").findList();        /*5*/
        return ok(
            views.html.users.render(users)                              /*6*/
        );
    }
}