
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
object listContainer extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Html,Int,Seq[Item],Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(header:Html)(level:Int, items:Seq[Item])(footer:Html):play.api.templates.Html = {
        _display_ {import models.Item


Seq[Any](format.raw/*1.56*/("""

"""),format.raw/*4.1*/("""
<div style="margin-left: """),_display_(Seq[Any](/*5.27*/{5*level})),format.raw/*5.36*/("""%">
    """),_display_(Seq[Any](/*6.6*/header)),format.raw/*6.12*/("""    
    <ul id="list"""),_display_(Seq[Any](/*7.18*/level)),format.raw/*7.23*/("""">

        """),_display_(Seq[Any](/*9.10*/items/*9.15*/.map/*9.19*/ { item =>_display_(Seq[Any](format.raw/*9.29*/("""
            """),_display_(Seq[Any](/*10.14*/listItem(item))),format.raw/*10.28*/("""
        """)))})),format.raw/*11.10*/("""

    </ul>
    """),_display_(Seq[Any](/*14.6*/footer)),format.raw/*14.12*/("""
</div>"""))}
    }
    
    def render(header:Html,level:Int,items:Seq[Item],footer:Html): play.api.templates.Html = apply(header)(level,items)(footer)
    
    def f:((Html) => (Int,Seq[Item]) => (Html) => play.api.templates.Html) = (header) => (level,items) => (footer) => apply(header)(level,items)(footer)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Mar 23 15:29:45 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap3/play-sbook/app/views/listContainer.scala.html
                    HASH: 7621d19cb491729338c58e08668e3265a74e55c2
                    MATRIX: 530->1|680->55|708->77|770->104|800->113|843->122|870->128|927->150|953->155|1001->168|1014->173|1026->177|1073->187|1123->201|1159->215|1201->225|1253->242|1281->248
                    LINES: 19->1|23->1|25->4|26->5|26->5|27->6|27->6|28->7|28->7|30->9|30->9|30->9|30->9|31->10|31->10|32->11|35->14|35->14
                    -- GENERATED --
                */
            