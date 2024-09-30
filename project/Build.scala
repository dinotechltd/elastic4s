import sbt._
import sbt.plugins.JvmPlugin
import sbt.Keys._

object Build extends AutoPlugin {

  override def trigger  = AllRequirements
  override def requires = JvmPlugin

  object autoImport {
    val org                    = "com.dinotech.elastic4s"
    val AkkaVersion            = "2.5.23"
    val AkkaHttpVersion        = "10.1.9"
    val CatsVersion            = "2.0.0"
    val CirceVersion           = "0.12.0-M3"
    val CommonsIoVersion       = "2.4"
    val ElasticsearchVersion   = "6.7.2"
    val ExtsVersion            = "1.61.1"
    val JacksonVersion         = "2.10.0"
    val Json4sVersion          = "3.6.7"
    val SprayJsonVersion       = "1.3.5"
    val AWSJavaSdkVersion      = "1.11.342"
    val Log4jVersion           = "2.9.1"
    val MockitoVersion         = "1.9.5"
    val PlayJsonVersion        = "2.7.4"
    val ReactiveStreamsVersion = "1.0.2"
    val ScalatestVersion       = "3.0.8"
    val ScalamockVersion       = "4.3.0"
    val Slf4jVersion           = "1.7.25"
  }

  import autoImport._

  def dinoRepo(isSnapshot: Boolean): Option[MavenRepository] = {
    if (isSnapshot)
      Some("Dino artifactory" at "https://artifactory.dinotech.io/artifactory/dino-artifacts;build.timestamp=" + new java.util.Date().getTime)
    else Some("Dino artifactory" at "https://artifactory.dinotech.io/artifactory/dino-artifacts")
  }

  override def projectSettings = Seq(
    organization := org,
    scalaVersion := "2.13.0",
    crossScalaVersions := Seq("2.13.0", "2.11.12", "2.12.8"),
    resolvers += "Artifactory Realm" at "https://artifactory.dinotech.io/artifactory/dino-artifacts/",
    javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled"),
    publishArtifact in Test := false,
    fork in Test:= false,
    parallelExecution in ThisBuild := false,
    credentials += Credentials(baseDirectory.value / ".sbt" / ".credentials"),
    scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8"),
    javacOptions := Seq("-source", "1.8", "-target", "1.8"),
    libraryDependencies ++= Seq(
      "com.sksamuel.exts" %% "exts"       % ExtsVersion,
      "org.slf4j"         % "slf4j-api"   % Slf4jVersion,
      "org.mockito"       % "mockito-all" % MockitoVersion % "test",
      "org.scalatest"     %% "scalatest"  % ScalatestVersion % "test"
    ),
    publishTo := dinoRepo(isSnapshot.value),
    pomExtra :=
      <url>https://github.com/dinotech/elastic4s</url>
        <licenses>
          <license>
            <name>Apache 2</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:dinotech/elastic4s.git</url>
          <connection>scm:git@github.com:dinotech/elastic4s.git</connection>
        </scm>
        <developers>
          <developer>
            <id>dinotech</id>
            <name>dinotech</name>
            <url>http://github.com/dinotech</url>
          </developer>
        </developers>
  )
}
