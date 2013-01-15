// @SOURCE:/home/noootsab/src/book_chapters/chap4/play-jbook/conf/routes
// @HASH:ea50f284f8ee7da244c388243c2473acb459d370
// @DATE:Tue Jan 15 21:10:37 CET 2013

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {


// @LINE:6
val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart("/"))))
                    

// @LINE:10
val controllers_Data_show1 = Route("GET", PathPattern(List(StaticPart("/data"))))
                    

// @LINE:11
val controllers_Data_allUsers2 = Route("GET", PathPattern(List(StaticPart("/users"))))
                    

// @LINE:12
val controllers_Data_post3 = Route("POST", PathPattern(List(StaticPart("/data/post"))))
                    

// @LINE:16
val controllers_Assets_at4 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    

// @LINE:19
val controllers_JDBC_page5 = Route("GET", PathPattern(List(StaticPart("/test/page"))))
                    

// @LINE:20
val controllers_JDBC_table6 = Route("GET", PathPattern(List(StaticPart("/test/create"))))
                    

// @LINE:21
val controllers_JDBC_test7 = Route("GET", PathPattern(List(StaticPart("/test/"),DynamicPart("value", """[^/]+"""))))
                    
def documentation = List(("""GET""","""/""","""controllers.Application.index()"""),("""GET""","""/data""","""controllers.Data.show()"""),("""GET""","""/users""","""controllers.Data.allUsers()"""),("""POST""","""/data/post""","""controllers.Data.post()"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""","""/test/page""","""controllers.JDBC.page"""),("""GET""","""/test/create""","""controllers.JDBC.table"""),("""GET""","""/test/$value<[^/]+>""","""controllers.JDBC.test(value:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil))
   }
}
                    

// @LINE:10
case controllers_Data_show1(params) => {
   call { 
        invokeHandler(_root_.controllers.Data.show(), HandlerDef(this, "controllers.Data", "show", Nil))
   }
}
                    

// @LINE:11
case controllers_Data_allUsers2(params) => {
   call { 
        invokeHandler(_root_.controllers.Data.allUsers(), HandlerDef(this, "controllers.Data", "allUsers", Nil))
   }
}
                    

// @LINE:12
case controllers_Data_post3(params) => {
   call { 
        invokeHandler(_root_.controllers.Data.post(), HandlerDef(this, "controllers.Data", "post", Nil))
   }
}
                    

// @LINE:16
case controllers_Assets_at4(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    

// @LINE:19
case controllers_JDBC_page5(params) => {
   call { 
        invokeHandler(_root_.controllers.JDBC.page, HandlerDef(this, "controllers.JDBC", "page", Nil))
   }
}
                    

// @LINE:20
case controllers_JDBC_table6(params) => {
   call { 
        invokeHandler(_root_.controllers.JDBC.table, HandlerDef(this, "controllers.JDBC", "table", Nil))
   }
}
                    

// @LINE:21
case controllers_JDBC_test7(params) => {
   call(params.fromPath[String]("value", None)) { (value) =>
        invokeHandler(_root_.controllers.JDBC.test(value), HandlerDef(this, "controllers.JDBC", "test", Seq(classOf[String])))
   }
}
                    
}
    
}
                