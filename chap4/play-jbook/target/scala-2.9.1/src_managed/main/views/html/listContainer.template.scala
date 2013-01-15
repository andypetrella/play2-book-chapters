
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
object listContainer extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Html,Chat,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(header:Html)(chat:Chat)(footer:Html):play.api.templates.Html = {
        _display_ {import models.Chat


Seq[Any](format.raw/*1.39*/("""

"""),format.raw/*4.1*/("""
<div class="chat" style="margin-left: """),_display_(Seq[Any](/*5.40*/{5*chat.occurrence})),format.raw/*5.59*/("""%">
    """),_display_(Seq[Any](/*6.6*/header)),format.raw/*6.12*/("""    
    <ul>
        """),_display_(Seq[Any](/*8.10*/chat/*8.14*/.items.map/*8.24*/ { item =>_display_(Seq[Any](format.raw/*8.34*/("""
            """),_display_(Seq[Any](/*9.14*/listItem(item))),format.raw/*9.28*/("""
        """)))})),format.raw/*10.10*/("""
    </ul>
    """),_display_(Seq[Any](/*12.6*/footer)),format.raw/*12.12*/("""
</div>"""))}
    }
    
    def render(header:Html,chat:Chat,footer:Html) = apply(header)(chat)(footer)
    
    def f:((Html) => (Chat) => (Html) => play.api.templates.Html) = (header) => (chat) => (footer) => apply(header)(chat)(footer)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jan 15 21:10:37 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap4/play-jbook/app/views/listContainer.scala.html
                    HASH: fea0202288e2dfbda5bc29d2741ad3a1eedf832d
                    MATRIX: 771->1|904->38|932->60|1007->100|1047->119|1090->128|1117->134|1175->157|1187->161|1205->171|1252->181|1301->195|1336->209|1378->219|1429->235|1457->241
                    LINES: 27->1|31->1|33->4|34->5|34->5|35->6|35->6|37->8|37->8|37->8|37->8|38->9|38->9|39->10|41->12|41->12
                    -- GENERATED --
                */
            