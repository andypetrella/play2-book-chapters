
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
object showUser extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[User,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user:User):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.13*/("""

<div>
    <h2>"""),_display_(Seq[Any](/*4.10*/user/*4.14*/.name)),format.raw/*4.19*/(""" ["""),_display_(Seq[Any](/*4.22*/user/*4.26*/.email)),format.raw/*4.32*/("""]</h2>
    <div class="bio">
        """),_display_(Seq[Any](/*6.10*/if(user.female)/*6.25*/{_display_(Seq[Any](format.raw/*6.26*/("""She""")))}/*6.30*/else/*6.34*/{_display_(Seq[Any](format.raw/*6.35*/("""He""")))})),format.raw/*6.38*/(""" has """),_display_(Seq[Any](/*6.44*/user/*6.48*/.age)),format.raw/*6.52*/(""" years old and lives at
        """),_display_(Seq[Any](/*7.10*/if(user.address != null)/*7.34*/{_display_(Seq[Any](format.raw/*7.35*/("""
            <address>
                """),_display_(Seq[Any](/*9.18*/user/*9.22*/.address.fullStreet)),format.raw/*9.41*/("""<br/>
                """),_display_(Seq[Any](/*10.18*/user/*10.22*/.address.county)),format.raw/*10.37*/("""<br/>
                """),_display_(Seq[Any](/*11.18*/user/*11.22*/.address.country)),format.raw/*11.38*/("""<br/>
            </address>
        """)))})),format.raw/*13.10*/("""
    </div>
</div>"""))}
    }
    
    def render(user:User) = apply(user)
    
    def f:((User) => play.api.templates.Html) = (user) => apply(user)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jan 15 21:10:37 CET 2013
                    SOURCE: /home/noootsab/src/book_chapters/chap4/play-jbook/app/views/showUser.scala.html
                    HASH: d4f74061f7f1bf2137e5327c7a8ec9d73b1b8aae
                    MATRIX: 756->1|844->12|896->29|908->33|934->38|972->41|984->45|1011->51|1084->89|1107->104|1145->105|1167->109|1179->113|1217->114|1251->117|1292->123|1304->127|1329->131|1397->164|1429->188|1467->189|1542->229|1554->233|1594->252|1653->275|1666->279|1703->294|1762->317|1775->321|1813->337|1883->375
                    LINES: 27->1|30->1|33->4|33->4|33->4|33->4|33->4|33->4|35->6|35->6|35->6|35->6|35->6|35->6|35->6|35->6|35->6|35->6|36->7|36->7|36->7|38->9|38->9|38->9|39->10|39->10|39->10|40->11|40->11|40->11|42->13
                    -- GENERATED --
                */
            