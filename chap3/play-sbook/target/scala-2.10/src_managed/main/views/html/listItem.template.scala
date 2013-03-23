
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
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
                    DATE: Sat Mar 23 15:29:45 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap3/play-sbook/app/views/listItem.scala.html
                    HASH: 2b053b4533ec0c90ee23a1beb4a02a180174718a
                    MATRIX: 506->1|613->12|641->34|694->60|722->61|805->118|832->119|903->154|916->158|943->163|996->180|1009->184|1041->194|1089->206|1102->210|1132->218
                    LINES: 19->1|23->1|25->4|27->6|27->6|29->8|29->8|32->11|32->11|32->11|32->11|32->11|32->11|32->11|32->11|32->11
                    -- GENERATED --
                */
            