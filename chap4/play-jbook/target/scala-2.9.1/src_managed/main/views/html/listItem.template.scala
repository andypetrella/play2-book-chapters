
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
object listItem extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Item,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(item:Item):play.api.templates.Html = {
        _display_ {import models.Item


Seq[Any](format.raw/*1.13*/("""

"""),format.raw/*4.1*/("""
<li class="item"><span>"""),_display_(Seq[Any](/*5.25*/item/*5.29*/.user)),format.raw/*5.34*/("""</span> <span class="time">["""),_display_(Seq[Any](/*5.63*/item/*5.67*/.timestamp)),format.raw/*5.77*/("""]</span> > """),_display_(Seq[Any](/*5.89*/item/*5.93*/.message)),format.raw/*5.101*/("""</li>"""))}
    }
    
    def render(item:Item) = apply(item)
    
    def f:((Item) => play.api.templates.Html) = (item) => apply(item)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jan 15 21:10:37 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap4/play-jbook/app/views/listItem.scala.html
                    HASH: a87f84d26daf654a07bebb182a1027b18c38a8a8
                    MATRIX: 756->1|863->12|891->34|951->59|963->63|989->68|1053->97|1065->101|1096->111|1143->123|1155->127|1185->135
                    LINES: 27->1|31->1|33->4|34->5|34->5|34->5|34->5|34->5|34->5|34->5|34->5|34->5
                    -- GENERATED --
                */
            