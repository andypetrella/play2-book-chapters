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

import static play.libs.F.*;

/** Uncomment the following lines as needed **/
/**
import akka.util.*;
import akka.actor.*;
import java.util.concurrent.*;
**/

public class Real extends Controller {

  public static WebSocket<String> so_web() {
    return new WebSocket<String>() {

      // Called when the Websocket Handshake is done.
      public void onReady(WebSocket.In<String> in, final WebSocket.Out<String> out) {

        // For each event received on the socket,
        in.onMessage(new Callback<String>() {
           public void invoke(String event) {

             // Log events to the console
             System.out.println(event);

           }
        });

        // When the socket is closed.
        in.onClose(new Callback0() {
           public void invoke() {

             System.out.println("Disconnected");

           }
        });

        // Send a single 'Hello!' message
        out.write("Let' Go!");

        Thread t = new Thread() {
            public int count = 0;
            @Override
            public void run() {
              while(++count > 0) {
                out.write("msg  :  " + count);
                try {
                  Thread.sleep(2000);
                } catch (Exception e) {
                  e.printStackTrace();
                  out.close();
                }
              }
            }
        };
        t.start();

      }

    };
  }


}