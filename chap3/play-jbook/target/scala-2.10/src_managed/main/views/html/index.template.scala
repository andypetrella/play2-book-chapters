
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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,List[Chat],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(message: String)(chats:List[Chat]):play.api.templates.Html = {
        _display_ {import models.Item

import java.util.List


Seq[Any](format.raw/*1.37*/("""

"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/main("Welcome the Packt Plublishing's Play 2.0 Demo")/*6.55*/ {_display_(Seq[Any](format.raw/*6.57*/("""
    <h1>"""),_display_(Seq[Any](/*7.10*/message)),format.raw/*7.17*/("""</h1>
    """),_display_(Seq[Any](/*8.6*/chats/*8.11*/.sortBy(_.occurrence).groupBy(_.date.toDate).toSeq.sortBy(_._1).map/*8.78*/ { dateAndChats =>_display_(Seq[Any](format.raw/*8.96*/("""
        <h3>-- Chat """),_display_(Seq[Any](/*9.22*/dateAndChats/*9.34*/._1)),format.raw/*9.37*/(""" --</h3>
        """),_display_(Seq[Any](/*10.10*/dateAndChats/*10.22*/._2.map/*10.29*/ { chat =>_display_(Seq[Any](format.raw/*10.39*/("""
            """),_display_(Seq[Any](/*11.14*/listContainer/*11.27*/{_display_(Seq[Any](format.raw/*11.28*/("""
                <h3>-- Chat #"""),_display_(Seq[Any](/*12.31*/chat/*12.35*/.occurrence)),format.raw/*12.46*/(""" --</h3>
            """)))}/*13.14*/(chat)/*13.20*/ {_display_(Seq[Any](format.raw/*13.22*/("""
                <hr/>
            """)))})),format.raw/*15.14*/("""
        """)))})),format.raw/*16.10*/("""
    """)))})),format.raw/*17.6*/("""
""")))})))}
    }
    
    def render(message:String,chats:List[Chat]): play.api.templates.Html = apply(message)(chats)
    
    def f:((String) => (List[Chat]) => play.api.templates.Html) = (message) => (chats) => apply(message)(chats)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Mar 23 15:21:44 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap3/play-jbook/app/views/index.scala.html
                    HASH: f4f722a717457d73744832c590a52bd6a3ba368a
                    MATRIX: 734->1|888->36|916->81|952->83|1013->136|1052->138|1097->148|1125->155|1170->166|1183->171|1258->238|1313->256|1370->278|1390->290|1414->293|1468->311|1489->323|1505->330|1553->340|1603->354|1625->367|1664->368|1731->399|1744->403|1777->414|1818->436|1833->442|1873->444|1941->480|1983->490|2020->496
                    LINES: 26->1|32->1|34->5|35->6|35->6|35->6|36->7|36->7|37->8|37->8|37->8|37->8|38->9|38->9|38->9|39->10|39->10|39->10|39->10|40->11|40->11|40->11|41->12|41->12|41->12|42->13|42->13|42->13|44->15|45->16|46->17
                    -- GENERATED --
                */
            