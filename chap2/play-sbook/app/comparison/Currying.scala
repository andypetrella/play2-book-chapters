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

    //those following function are partially applied (note the vals)
    val showInEn = showMessage(_:String)("en")
    val showInFr = showMessage(_:String)("fr")

    //call it will output Hello! in the REPL
    val showWelcomeInEn = showInEn("welcome")
    //call it will output Salut! in the REPL
    val showWelcomeInFr = showInFr("welcome")

}