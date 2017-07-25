organization := "com.quaildev.scalamock"
name := "scalamock-demo"
version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.twitter" %% "chill" % "0.9.2",
  "net.debasishg" %% "redisclient" % "3.4",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test,
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)
