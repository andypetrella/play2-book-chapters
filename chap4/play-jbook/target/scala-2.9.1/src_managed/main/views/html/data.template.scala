
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
object data extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[User],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(userForm:Form[User]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.23*/("""
"""),_display_(Seq[Any](/*2.2*/main("Data tests")/*2.20*/ {_display_(Seq[Any](format.raw/*2.22*/("""
    <style>
        label """),format.raw("""{"""),format.raw/*4.16*/("""
            color: blue;
        """),format.raw("""}"""),format.raw/*6.10*/("""
        dd.info """),format.raw("""{"""),format.raw/*7.18*/("""
            color: green;
        """),format.raw("""}"""),format.raw/*9.10*/("""
        dd.error """),format.raw("""{"""),format.raw/*10.19*/("""
            color: red;
        """),format.raw("""}"""),format.raw/*12.10*/("""
    </style>

    """),_display_(Seq[Any](/*15.6*/if(userForm.hasErrors)/*15.28*/ {_display_(Seq[Any](format.raw/*15.30*/("""
        <div style="background-color:red; color:white;">The form has """),_display_(Seq[Any](/*16.71*/userForm/*16.79*/.errors.size)),format.raw/*16.91*/(""" errors</div>
    """)))})),format.raw/*17.6*/("""
    """),_display_(Seq[Any](/*18.6*/if(userForm.value.isDefined)/*18.34*/ {_display_(Seq[Any](format.raw/*18.36*/("""
        <h1>"""),_display_(Seq[Any](/*19.14*/userForm/*19.22*/.get.name)),format.raw/*19.31*/(""" ("""),_display_(Seq[Any](/*19.34*/userForm/*19.42*/.get.age)),format.raw/*19.50*/(""")</h1>
        <h2>Lives at """),_display_(Seq[Any](/*20.23*/userForm/*20.31*/.get.address.fullStreet)),format.raw/*20.54*/("""</h2>
    """)))}/*21.7*/else/*21.12*/{_display_(Seq[Any](format.raw/*21.13*/("""
        <h1>Feed some data</h1>
        """),_display_(Seq[Any](/*23.10*/helper/*23.16*/.form(action = routes.Data.post())/*23.50*/ {_display_(Seq[Any](format.raw/*23.52*/("""
            """),_display_(Seq[Any](/*24.14*/helper/*24.20*/.inputText(userForm("name")))),format.raw/*24.48*/("""

            """),_display_(Seq[Any](/*26.14*/helper/*26.20*/.input(userForm("email"))/*26.45*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*26.74*/("""
                <input type="email" name=""""),_display_(Seq[Any](/*27.44*/name)),format.raw/*27.48*/("""" id=""""),_display_(Seq[Any](/*27.55*/id)),format.raw/*27.57*/("""" """),_display_(Seq[Any](/*27.60*/toHtmlArgs(args))),format.raw/*27.76*/(""">
            """)))})),format.raw/*28.14*/("""
            """),_display_(Seq[Any](/*29.14*/helper/*29.20*/.input(userForm("age"))/*29.43*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*29.72*/("""
                <input type="number" name=""""),_display_(Seq[Any](/*30.45*/name)),format.raw/*30.49*/("""" id=""""),_display_(Seq[Any](/*30.56*/id)),format.raw/*30.58*/("""" """),_display_(Seq[Any](/*30.61*/toHtmlArgs(args))),format.raw/*30.77*/(""">
            """)))})),format.raw/*31.14*/("""

            """),_display_(Seq[Any](/*33.14*/helper/*33.20*/.inputRadioGroup(
                userForm("female"),
                options = helper.options("true"->"Female", "false"->"Male")
            ))),format.raw/*36.14*/("""
            <fieldset>
                <legend>Address</legend>
                """),_display_(Seq[Any](/*39.18*/helper/*39.24*/.inputText(userForm("address.fullStreet")))),format.raw/*39.66*/("""
                """),_display_(Seq[Any](/*40.18*/helper/*40.24*/.inputText(
                    userForm("address.county"),
                    '_label -> "County"
                ))),format.raw/*43.18*/("""
                """),_display_(Seq[Any](/*44.18*/helper/*44.24*/.select(userForm("address.country"), Seq(
                    "" -> "---",
                    "AR" -> "Arda",
                    "BE" -> "Belgium",
                    "SL" -> "Smurfs Land")
                ))),format.raw/*49.18*/("""
            </fieldset>

            <input type="submit" name="send" value="Feed" />
        """)))})),format.raw/*53.10*/("""
    """)))})),format.raw/*54.6*/("""
""")))})))}
    }
    
    def render(userForm:Form[User]) = apply(userForm)
    
    def f:((Form[User]) => play.api.templates.Html) = (userForm) => apply(userForm)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jan 15 21:10:37 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap4/play-jbook/app/views/data.scala.html
                    HASH: 15aaf52ab8026c068fcdeac148245ccbffdbeb25
                    MATRIX: 758->1|856->22|892->24|918->42|957->44|1031->72|1112->107|1176->125|1258->161|1324->180|1405->214|1460->234|1491->256|1531->258|1638->329|1655->337|1689->349|1739->368|1780->374|1817->402|1857->404|1907->418|1924->426|1955->435|1994->438|2011->446|2041->454|2106->483|2123->491|2168->514|2197->526|2210->531|2249->532|2327->574|2342->580|2385->614|2425->616|2475->630|2490->636|2540->664|2591->679|2606->685|2640->710|2707->739|2787->783|2813->787|2856->794|2880->796|2919->799|2957->815|3004->830|3054->844|3069->850|3101->873|3168->902|3249->947|3275->951|3318->958|3342->960|3381->963|3419->979|3466->994|3517->1009|3532->1015|3697->1158|3815->1240|3830->1246|3894->1288|3948->1306|3963->1312|4102->1429|4156->1447|4171->1453|4403->1663|4531->1759|4568->1765
                    LINES: 27->1|30->1|31->2|31->2|31->2|33->4|35->6|36->7|38->9|39->10|41->12|44->15|44->15|44->15|45->16|45->16|45->16|46->17|47->18|47->18|47->18|48->19|48->19|48->19|48->19|48->19|48->19|49->20|49->20|49->20|50->21|50->21|50->21|52->23|52->23|52->23|52->23|53->24|53->24|53->24|55->26|55->26|55->26|55->26|56->27|56->27|56->27|56->27|56->27|56->27|57->28|58->29|58->29|58->29|58->29|59->30|59->30|59->30|59->30|59->30|59->30|60->31|62->33|62->33|65->36|68->39|68->39|68->39|69->40|69->40|72->43|73->44|73->44|78->49|82->53|83->54
                    -- GENERATED --
                */
            