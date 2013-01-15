// @SOURCE:/home/noootsab/src/book_chapters/chap4/play-jbook/conf/routes
// @HASH:ea50f284f8ee7da244c388243c2473acb459d370
// @DATE:Tue Jan 15 21:10:37 CET 2013

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:16
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:6
package controllers {

// @LINE:21
// @LINE:20
// @LINE:19
class ReverseJDBC {
    


 
// @LINE:19
def page() = {
   Call("GET", "/test/page")
}
                                                        
 
// @LINE:20
def table() = {
   Call("GET", "/test/create")
}
                                                        
 
// @LINE:21
def test(value:String) = {
   Call("GET", "/test/" + implicitly[PathBindable[String]].unbind("value", value))
}
                                                        

                      
    
}
                            

// @LINE:6
class ReverseApplication {
    


 
// @LINE:6
def index() = {
   Call("GET", "/")
}
                                                        

                      
    
}
                            

// @LINE:16
class ReverseAssets {
    


 
// @LINE:16
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            

// @LINE:12
// @LINE:11
// @LINE:10
class ReverseData {
    


 
// @LINE:11
def allUsers() = {
   Call("GET", "/users")
}
                                                        
 
// @LINE:12
def post() = {
   Call("POST", "/data/post")
}
                                                        
 
// @LINE:10
def show() = {
   Call("GET", "/data")
}
                                                        

                      
    
}
                            
}
                    


// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:16
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:6
package controllers.javascript {

// @LINE:21
// @LINE:20
// @LINE:19
class ReverseJDBC {
    


 
// @LINE:19
def page = JavascriptReverseRoute(
   "controllers.JDBC.page",
   """
      function() {
      return _wA({method:"GET", url:"/test/page"})
      }
   """
)
                                                        
 
// @LINE:20
def table = JavascriptReverseRoute(
   "controllers.JDBC.table",
   """
      function() {
      return _wA({method:"GET", url:"/test/create"})
      }
   """
)
                                                        
 
// @LINE:21
def test = JavascriptReverseRoute(
   "controllers.JDBC.test",
   """
      function(value) {
      return _wA({method:"GET", url:"/test/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("value", value)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:6
class ReverseApplication {
    


 
// @LINE:6
def index = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"/"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:16
class ReverseAssets {
    


 
// @LINE:16
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:12
// @LINE:11
// @LINE:10
class ReverseData {
    


 
// @LINE:11
def allUsers = JavascriptReverseRoute(
   "controllers.Data.allUsers",
   """
      function() {
      return _wA({method:"GET", url:"/users"})
      }
   """
)
                                                        
 
// @LINE:12
def post = JavascriptReverseRoute(
   "controllers.Data.post",
   """
      function() {
      return _wA({method:"POST", url:"/data/post"})
      }
   """
)
                                                        
 
// @LINE:10
def show = JavascriptReverseRoute(
   "controllers.Data.show",
   """
      function() {
      return _wA({method:"GET", url:"/data"})
      }
   """
)
                                                        

                      
    
}
                            
}
                    


// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:16
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:6
package controllers.ref {

// @LINE:21
// @LINE:20
// @LINE:19
class ReverseJDBC {
    


 
// @LINE:19
def page() = new play.api.mvc.HandlerRef(
   controllers.JDBC.page(), HandlerDef(this, "controllers.JDBC", "page", Seq())
)
                              
 
// @LINE:20
def table() = new play.api.mvc.HandlerRef(
   controllers.JDBC.table(), HandlerDef(this, "controllers.JDBC", "table", Seq())
)
                              
 
// @LINE:21
def test(value:String) = new play.api.mvc.HandlerRef(
   controllers.JDBC.test(value), HandlerDef(this, "controllers.JDBC", "test", Seq(classOf[String]))
)
                              

                      
    
}
                            

// @LINE:6
class ReverseApplication {
    


 
// @LINE:6
def index() = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq())
)
                              

                      
    
}
                            

// @LINE:16
class ReverseAssets {
    


 
// @LINE:16
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            

// @LINE:12
// @LINE:11
// @LINE:10
class ReverseData {
    


 
// @LINE:11
def allUsers() = new play.api.mvc.HandlerRef(
   controllers.Data.allUsers(), HandlerDef(this, "controllers.Data", "allUsers", Seq())
)
                              
 
// @LINE:12
def post() = new play.api.mvc.HandlerRef(
   controllers.Data.post(), HandlerDef(this, "controllers.Data", "post", Seq())
)
                              
 
// @LINE:10
def show() = new play.api.mvc.HandlerRef(
   controllers.Data.show(), HandlerDef(this, "controllers.Data", "show", Seq())
)
                              

                      
    
}
                            
}
                    
                