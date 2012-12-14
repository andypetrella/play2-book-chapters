package controllers;

import play.*;
import play.mvc.*;
import static play.mvc.Results.*;
import play.libs.*;
import java.util.List;

import db.jdbc.SampleDb;

public class JDBC extends Controller {

    public static Result page() {
        return ok(views.html.jdbc.render());
    }

    public static Result table() {
        try {
            SampleDb.createTestTable();
            return ok("table created");
        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }

    public static Result test(String value) {
        try {
            SampleDb.insertTestData(value);
            List<String> vs = SampleDb.getTestData();
            return ok(Json.toJson(vs));
        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }

}