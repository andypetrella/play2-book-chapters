import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "play-sbook"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      scalacOptions in Test ++= Seq("-Xlint","-deprecation", "-unchecked"),
      javacOptions in Test ++= Seq("-Xlint","-deprecation", "-unchecked")
    )

}
