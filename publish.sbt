import sbt.url

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/linqing01/outside"),
    "scm:linqing01@github.com:linqing01/outside.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id = "linqing01",
    name = "linqing",
    email = "linqing01@gmail.com",
    url = url("http://github.com/linqing")
  )
)

ThisBuild / description := "Integrate outside resources"
ThisBuild / licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / homepage := Some(url("https://github.com/linqing01/outside"))

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / isSnapshot := false
ThisBuild / publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

ThisBuild / publishTo := sonatypePublishToBundle.value

ThisBuild / publishMavenStyle := true
