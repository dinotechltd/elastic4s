lazy val root = Project("elastic4s-http-client-sbt", file("."))
  .settings(name := "elastic4s-http-client-sbt")
  .settings(scalaVersion := "2.12.6")
  .settings(libraryDependencies ++= Seq(
    "com.dinotech.elastic4s" %% "elastic4s-http" % "6.3.3"
  ))
