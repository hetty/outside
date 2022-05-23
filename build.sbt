import Dependencies._

ThisBuild / scalaVersion     := "2.12.15"
ThisBuild / version          := "0.1.2"
ThisBuild / organization     := "com.starworking"
ThisBuild / organizationName := "starworking"

lazy val root = (project in file("."))
  .settings(
    name := "outside",
    libraryDependencies += "com.tencentcloudapi" % "tencentcloud-sdk-java" % "3.1.322",
    libraryDependencies += scalaTest % Test
  )
