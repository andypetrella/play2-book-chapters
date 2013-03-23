
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
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object listContainer extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Html,Chat,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(header:Html)(chat:Chat)(footer:Html):play.api.templates.Html = {
        _display_ {import models.Chat


Seq[Any](format.raw/*1.39*/("""

"""),format.raw/*4.1*/("""
<div style="margin-left: """),_display_(Seq[Any](/*5.27*/{5*chat.occurrence})),format.raw/*5.46*/("""%">
    """),_display_(Seq[Any](/*6.6*/header)),format.raw/*6.12*/("""    
    <ul>
        """),_display_(Seq[Any](/*8.10*/chat/*8.14*/.items.map/*8.24*/ { item =>_display_(Seq[Any](format.raw/*8.34*/("""
            """),_display_(Seq[Any](/*9.14*/listItem(item))),format.raw/*9.28*/("""
        """)))})),format.raw/*10.10*/("""
    </ul>
    """),_display_(Seq[Any](/*12.6*/footer)),format.raw/*12.12*/("""
</div>"""))}
    }
    
    def render(header:Html,chat:Chat,footer:Html): play.api.templates.Html = apply(header)(chat)(footer)
    
    def f:((Html) => (Chat) => (Html) => play.api.templates.Html) = (header) => (chat) => (footer) => apply(header)(chat)(footer)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Mar 23 15:21:44 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap3/play-jbook/app/views/listContainer.scala.html
                    HASH: fcd88704ec907589bca7e485966dea1c2e14e26a
                    MATRIX: 739->1|872->38|900->60|962->87|1002->106|1045->115|1072->121|1130->144|1142->148|1160->158|1207->168|1256->182|1291->196|1333->206|1384->222|1412->228
                    LINES: 26->1|30->1|32->4|33->5|33->5|34->6|34->6|36->8|36->8|36->8|36->8|37->9|37->9|38->10|40->12|40->12
                    -- GENERATED --
                */
            