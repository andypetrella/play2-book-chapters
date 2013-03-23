
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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,Int,Seq[Item],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(message: String)(level:Int, items:Seq[Item]):play.api.templates.Html = {
        _display_ {import models.Item


Seq[Any](format.raw/*1.47*/("""

"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/main("Welcome the Packt Plublishing's Play 2.0 Demo")/*5.55*/ {_display_(Seq[Any](format.raw/*5.57*/("""
    <h1>"""),_display_(Seq[Any](/*6.10*/message)),format.raw/*6.17*/("""</h1>
    """),_display_(Seq[Any](/*7.6*/listContainer/*7.19*/{_display_(Seq[Any](format.raw/*7.20*/("""
        <h"""),_display_(Seq[Any](/*8.12*/level)),format.raw/*8.17*/(""">Level """),_display_(Seq[Any](/*8.25*/level)),format.raw/*8.30*/("""</h"""),_display_(Seq[Any](/*8.34*/level)),format.raw/*8.39*/(""">
    """)))}/*9.6*/(level, items)/*9.20*/ {_display_(Seq[Any](format.raw/*9.22*/("""
        <div>-- end of Level"""),_display_(Seq[Any](/*10.30*/level)),format.raw/*10.35*/("""</div>
    """)))})),format.raw/*11.6*/("""
""")))})))}
    }
    
    def render(message:String,level:Int,items:Seq[Item]): play.api.templates.Html = apply(message)(level,items)
    
    def f:((String) => (Int,Seq[Item]) => play.api.templates.Html) = (message) => (level,items) => apply(message)(level,items)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Mar 23 15:29:45 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap3/play-sbook/app/views/index.scala.html
                    HASH: 20df8eb870d34c51811763745e5534827176fd06
                    MATRIX: 519->1|660->46|688->68|724->70|785->123|824->125|869->135|897->142|942->153|963->166|1001->167|1048->179|1074->184|1117->192|1143->197|1182->201|1208->206|1232->213|1254->227|1293->229|1359->259|1386->264|1429->276
                    LINES: 19->1|23->1|25->4|26->5|26->5|26->5|27->6|27->6|28->7|28->7|28->7|29->8|29->8|29->8|29->8|29->8|29->8|30->9|30->9|30->9|31->10|31->10|32->11
                    -- GENERATED --
                */
            