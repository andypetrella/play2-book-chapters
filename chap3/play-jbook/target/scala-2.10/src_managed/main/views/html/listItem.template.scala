
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
object listItem extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Item,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(item:Item):play.api.templates.Html = {
        _display_ {import models.Item


Seq[Any](format.raw/*1.13*/("""

"""),format.raw/*4.1*/("""
<style>
    li.item span """),format.raw/*6.18*/("""{"""),format.raw/*6.19*/("""
        width: 100px; display:inline-block;        
    """),format.raw/*8.5*/("""}"""),format.raw/*8.6*/("""
</style>

<li class="item"><span>"""),_display_(Seq[Any](/*11.25*/item/*11.29*/.user)),format.raw/*11.34*/("""</span> </span>["""),_display_(Seq[Any](/*11.51*/item/*11.55*/.timestamp)),format.raw/*11.65*/("""]</span> > """),_display_(Seq[Any](/*11.77*/item/*11.81*/.message)),format.raw/*11.89*/("""</li>"""))}
    }
    
    def render(item:Item): play.api.templates.Html = apply(item)
    
    def f:((Item) => play.api.templates.Html) = (item) => apply(item)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Mar 23 15:21:44 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap3/play-jbook/app/views/listItem.scala.html
                    HASH: 2b053b4533ec0c90ee23a1beb4a02a180174718a
                    MATRIX: 724->1|831->12|859->34|912->60|940->61|1023->118|1050->119|1121->154|1134->158|1161->163|1214->180|1227->184|1259->194|1307->206|1320->210|1350->218
                    LINES: 26->1|30->1|32->4|34->6|34->6|36->8|36->8|39->11|39->11|39->11|39->11|39->11|39->11|39->11|39->11|39->11
                    -- GENERATED --
                */
            