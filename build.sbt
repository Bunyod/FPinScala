name := "FPinScala"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.3",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
  "com.artofsolving" % "jodconverter" % "2.2.1",
  "org.typelevel" %% "cats-effect" % "0.9",
  "org.typelevel" %% "cats-effect-laws" % "0.9" % "test"