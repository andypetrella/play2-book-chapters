
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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,List[Chat],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(message: String)(chats:List[Chat]):play.api.templates.Html = {
        _display_ {import models.Item

import java.util.List


Seq[Any](format.raw/*1.37*/("""

"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/main("Welcome the Packt Publishing's Play 2.0 Demo")/*6.54*/ {_display_(Seq[Any](format.raw/*6.56*/("""
    <h1>"""),_display_(Seq[Any](/*7.10*/message)),format.raw/*7.17*/("""</h1>
    """),_display_(Seq[Any](/*8.6*/chats/*8.11*/.sortBy(_.occurrence).groupBy(_.date.toDate).toSeq.sortBy(_._1).map/*8.78*/ { dateAndChats =>_display_(Seq[Any](format.raw/*8.96*/("""
        <div class="date">
            <h2>-- Chat """),_display_(Seq[Any](/*10.26*/dateAndChats/*10.38*/._1)),format.raw/*10.41*/(""" --</h2>
            """),_display_(Seq[Any](/*11.14*/dateAndChats/*11.26*/._2.map/*11.33*/ { chat =>_display_(Seq[Any](format.raw/*11.43*/(""" 
                """),_display_(Seq[Any](/*12.18*/listContainer/*12.31*/{_display_(Seq[Any](format.raw/*12.32*/("""
                    <h3>-- Chat #"""),_display_(Seq[Any](/*13.35*/chat/*13.39*/.occurrence)),format.raw/*13.50*/(""" --</h3>
                """)))}(chat)/*14.24*/ {_display_(Seq[Any](format.raw/*14.26*/("""
                    <hr/>
                """)))})),format.raw/*16.18*/("""
            """)))})),format.raw/*17.14*/("""
        </div>
    """)))})),format.raw/*19.6*/("""
""")))})))}
    }
    
    def render(message:String,chats:List[Chat]) = apply(message)(chats)
    
    def f:((String) => (List[Chat]) => play.api.templates.Html) = (message) => (chats) => apply(message)(chats)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jan 15 21:10:37 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap4/play-jbook/app/views/index.scala.html
                    HASH: 8736663b531e39c7b79680479cd5e224905f5a52
                    MATRIX: 766->1|920->36|948->81|984->83|1044->135|1083->137|1128->147|1156->154|1201->165|1214->170|1289->237|1344->255|1433->308|1454->320|1479->323|1537->345|1558->357|1574->364|1622->374|1677->393|1699->406|1738->407|1809->442|1822->446|1855->457|1906->489|1946->491|2022->535|2068->549|2120->570
                    LINES: 27->1|33->1|35->5|36->6|36->6|36->6|37->7|37->7|38->8|38->8|38->8|38->8|40->10|40->10|40->10|41->11|41->11|41->11|41->11|42->12|42->12|42->12|43->13|43->13|43->13|44->14|44->14|46->16|47->17|49->19
                    -- GENERATED --
                */
            