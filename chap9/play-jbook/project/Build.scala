import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName     = "play-jbook"
  val appVersion    = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    //groupId      >   artifactId  >   version
    //"com.google.guava" %   "guava"   %   "13.0.1",
    "org.specs2" %% "specs2" % "1.12.2" % "test",
    "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
    "org.reflections" % "reflections" % "0.9.8",
    "org.seleniumhq.selenium" % "selenium-java" % "2.25.0" % "test"

    //"com.google.code.findbugs" % "jsr305" % "2.0.1" % "provided"
  )
  val localMavenRepo = "Local Maven Repository" at file(Path.userHome.absolutePath+"/.m2/repository").toURI.toURL.toString
  val noootsabSnapshots = "Noootsab SNAPSHOTS" at "https://repository-andy-petrella.forge.cloudbees.com/release/"

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
    resolvers ++= Seq(localMavenRepo, noootsabSnapshots),
    scalacOptions in Test ++= Seq("-Xlint","-deprecation", "-unchecked"),
    javacOptions in Test ++= Seq("-Xlint","-deprecation", "-unchecked")
  )
}