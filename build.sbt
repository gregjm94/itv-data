ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"
val sparkVersion = "3.2.1 "

// If spark was flagged as provided this would need the assembly sbt plugin
libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.2.11",
  "org.scalatest" %% "scalatest" % "3.2.11" % "test"
)

lazy val root = (project in file("."))
  .settings(
    name := "itv-data"
  )
