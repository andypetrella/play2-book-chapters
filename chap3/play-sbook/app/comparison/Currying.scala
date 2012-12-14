package comparison

import scala.collection.immutable.{Map => Map_, _} //because of the comparison.Map

object Currying {

    val messages = Map_(
        "en" -> Map_(
            "welcome" -> "Hello!",
            "bye" -> "See Ya!"
        ),
        "fr" -> Map_(
            "welcome" -> "Bonjour!",
            "bye" -> "À Plus!"
        )

    )

    def showMessage(msg:String)(lang:String) = println(messages(lang)(msg))

    def showInEn(msg:String) = showMessage(msg)("en")
    def showInFr(msg:String) = showMessage(msg)("fr")

    //call it will output Hello! in the REPL
    def showWelcomeInEn = showInEn("welcome")
    //call it will output Salut! in the REPL
    def showWelcomeInFr = showInFr("welcome")

}