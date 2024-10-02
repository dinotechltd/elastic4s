package com.dinotech.elastic4s.akka

import akka.http.scaladsl.settings.ConnectionPoolSettings
import com.typesafe.config.{Config, ConfigFactory}

import java.util.concurrent.TimeUnit
import scala.concurrent.duration._
import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.util.{Failure, Success, Try}

object AkkaHttpClientSettings {

  private def defaultConfig: Config = ConfigFactory.load().getConfig("com.dinotech.elastic4s.akka")

  lazy val default: AkkaHttpClientSettings = apply(defaultConfig)

  private def removeProtocol(uri: String) = uri
    .replaceAll("http://","")
    .replaceAll("https://","")
  private def configuredHttps(cfg: Config) = {
    (Try(cfg.getStringList("hosts")), Try(cfg.getString("host"))) match {
      case (Success(hosts), _) if hosts.asScala.nonEmpty && hasProtocolDefined(hosts.asScala.head) => isHttps(hosts.asScala.head)
      case (_, Success(host)) if hasProtocolDefined(host)=> isHttps(host)
      case _ => cfg.getBoolean("https")
    }
  }

  private def configuredHosts(cfg: Config) =
    (Try(cfg.getStringList("hosts")), Try(cfg.getString("host"))) match {
      case (Success(hosts), _) if hosts.asScala.nonEmpty => hosts.asScala.map(removeProtocol).toSeq
      case (_, Success(host)) => Seq(removeProtocol(host))
      case _ => Seq.empty
    }

  private def hasProtocolDefined(uri: String): Boolean = uri.startsWith("http://") || uri.startsWith("https://")
  private def isHttps(uri: String): Boolean = uri.startsWith("https://")

  def apply(config: Config): AkkaHttpClientSettings = {
    val cfg = config.withFallback(defaultConfig)
    val hosts = configuredHosts(cfg)
    val username = Try(cfg.getString("username")).map(Some(_)).getOrElse(None)
    val password = Try(cfg.getString("password")).map(Some(_)).getOrElse(None)
    val queueSize = cfg.getInt("queue-size")
    val https = configuredHttps(cfg)
    val blacklistMinDuration = Duration(cfg.getDuration("blacklist.min-duration", TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS)
    val blacklistMaxDuration = Duration(cfg.getDuration("blacklist.max-duration", TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS)
    val maxRetryTimeout = Duration(cfg.getDuration("max-retry-timeout", TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS)
    val poolSettings = ConnectionPoolSettings(cfg.withFallback(ConfigFactory.load()))
    AkkaHttpClientSettings(
      https,
      hosts.toVector,
      username,
      password,
      queueSize,
      poolSettings,
      blacklistMinDuration,
      blacklistMaxDuration,
      maxRetryTimeout
    )
  }

  def apply(): AkkaHttpClientSettings =
    default

  def apply(hosts: Seq[String]): AkkaHttpClientSettings =
    apply().copy(hosts = hosts.toVector)
}

case class AkkaHttpClientSettings(
                                   https: Boolean,
                                   hosts: Vector[String],
                                   username: Option[String],
                                   password: Option[String],
                                   queueSize: Int,
                                   poolSettings: ConnectionPoolSettings = ConnectionPoolSettings(ConfigFactory.load()),
                                   blacklistMinDuration: FiniteDuration = AkkaHttpClientSettings.default.blacklistMinDuration,
                                   blacklistMaxDuration: FiniteDuration = AkkaHttpClientSettings.default.blacklistMaxDuration,
                                   maxRetryTimeout: FiniteDuration = AkkaHttpClientSettings.default.maxRetryTimeout
                                 ) {
  def hasCredentialsDefined: Boolean = username.isDefined && password.isDefined
}
