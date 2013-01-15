
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
object jdbc extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.Html] {

    /**/
    def apply/*1.2*/():play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.4*/("""

"""),_display_(Seq[Any](/*3.2*/main("jdbc test")/*3.19*/ {_display_(Seq[Any](format.raw/*3.21*/("""
    <script>
        $(function() """),format.raw("""{"""),format.raw/*5.23*/("""
            var result = $("#result");

            var showResult = function showResult(results) """),format.raw("""{"""),format.raw/*8.60*/("""
                result.empty();
                if ($.isArray(results)) results = results; else  results = [results];
                for (var i=0, l = results.length; i < l ; i++) """),format.raw("""{"""),format.raw/*11.65*/("""
                    result.append("<li>"+results[i]+"</li>");
                """),format.raw("""}"""),format.raw/*13.18*/("""
            """),format.raw("""}"""),format.raw/*14.14*/(""";

            $("a").click(function(e) """),format.raw("""{"""),format.raw/*16.39*/("""
                e.stopImmediatePropagation();
                e.stopPropagation();
                e.preventDefault();
                $.get($(this).attr("href"), function(d) """),format.raw("""{"""),format.raw/*20.58*/("""showResult(d);"""),format.raw("""}"""),format.raw/*20.73*/(""");
                $(this)
                    .parent()
                        .hide()
                    .next()
                        .show();
                return false;
            """),format.raw("""}"""),format.raw/*27.14*/(""");
        """),format.raw("""}"""),format.raw/*28.10*/(""");
    </script>


    <ol>
        <li><a href=""""),_display_(Seq[Any](/*33.23*/routes/*33.29*/.JDBC.table)),format.raw/*33.40*/("""">Create table</a></li>
        <li style="display:none;list-style:none;"><a href=""""),_display_(Seq[Any](/*34.61*/routes/*34.67*/.JDBC.test("Don''t insert me"))),format.raw/*34.97*/("""">first val</a></li>
        <li style="display:none;list-style:none;"><a href=""""),_display_(Seq[Any](/*35.61*/routes/*35.67*/.JDBC.test("What did I said?"))),format.raw/*35.97*/("""">second val</a></li>
        <li style="display:none;list-style:none;"><a href=""""),_display_(Seq[Any](/*36.61*/routes/*36.67*/.JDBC.test("Grrr"))),format.raw/*36.85*/("""">third val</a></li>
        <li style="display:none;list-style:none;">End</li>
    </ol>
    <ul id="result">
    </ul>
""")))})))}
    }
    
    def render() = apply()
    
    def f:(() => play.api.templates.Html) = () => apply()
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jan 15 21:10:37 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap4/play-jbook/app/views/jdbc.scala.html
                    HASH: 9cc86403f60cc3d9989524f74f1d7108a43bdfaa
                    MATRIX: 747->1|825->3|862->6|887->23|926->25|1008->61|1154->161|1384->344|1511->424|1572->438|1660->479|1884->656|1946->671|2186->864|2245->876|2331->926|2346->932|2379->943|2499->1027|2514->1033|2566->1063|2683->1144|2698->1150|2750->1180|2868->1262|2883->1268|2923->1286
                    LINES: 27->1|30->1|32->3|32->3|32->3|34->5|37->8|40->11|42->13|43->14|45->16|49->20|49->20|56->27|57->28|62->33|62->33|62->33|63->34|63->34|63->34|64->35|64->35|64->35|65->36|65->36|65->36
                    -- GENERATED --
                */
            