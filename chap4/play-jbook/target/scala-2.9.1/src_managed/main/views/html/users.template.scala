
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.api.templates.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import com.avaje.ebean._
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object users extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[List[User],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(users:List[User]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.20*/("""

"""),_display_(Seq[Any](/*3.2*/main("All users")/*3.19*/ {_display_(Seq[Any](format.raw/*3.21*/("""
    """),_display_(Seq[Any](/*4.6*/users/*4.11*/.map/*4.15*/ { u =>_display_(Seq[Any](format.raw/*4.22*/("""
        """),_display_(Seq[Any](/*5.10*/showUser(u))),format.raw/*5.21*/("""
    """)))})),format.raw/*6.6*/("""
""")))})))}
    }
    
    def render(users:List[User]) = apply(users)
    
    def f:((List[User]) => play.api.templates.Html) = (users) => apply(users)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jan 15 21:10:37 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap4/play-jbook/app/views/users.scala.html
                    HASH: ebe731d1f3fb83d866b37af820100060a98a2fb7
                    MATRIX: 759->1|854->19|891->22|916->39|955->41|995->47|1008->52|1020->56|1064->63|1109->73|1141->84|1177->90
                    LINES: 27->1|30->1|32->3|32->3|32->3|33->4|33->4|33->4|33->4|34->5|34->5|35->6
                    -- GENERATED --
                */
            