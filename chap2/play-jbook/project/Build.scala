import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "play-jbook"
    val appVersion      = "1.0-SNAPSHOT"


    val appDependencies = Seq(
        //groupId          >   artifactId  >   version
        //"com.google.guava" %   "guava"     %   "12.0.1"
    )

    val localMavenRepo = "Local Maven Repository" at file(Path.userHome.absolutePath+"/.m2/repository").toURI.toURL.toString

    val noootsabSnapshots = "Noootsab SNAPSHOTS" at "https://repository-andy-petrella.forge.cloudbees.com/release/"

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
        resolvers ++= Seq(localMavenRepo, noootsabSnapshots)
    )

}
