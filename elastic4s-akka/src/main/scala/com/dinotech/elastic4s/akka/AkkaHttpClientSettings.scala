package com.dinotech.elastic4s.akka

import akka.http.scaladsl.settings.ConnectionPoolSettings
import com.typesafe.config.{Config, ConfigFactory}

import java.util.concurrent.TimeUnit
import scala.concurrent.duration._
import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.util.Try

object AkkaHttpClientSettings {

  private def defaultConfig: Config = ConfigFactory.load().getConfig("com.dinotech.elastic4s.akka")

  lazy val default: AkkaHttpClientSettings = apply(defaultConfig)

  def apply(config: Config): AkkaHttpClientSettings = {
    val cfg = config.withFallback(defaultConfig)
    val hosts = cfg.getStringList("hosts").asScala.toVector
    val username = Try(cfg.getString("username")).map(Some(_)).getOrElse(None)
    val password = Try(cfg.getString("password")).map(Some(_)).getOrElse(None)
    val queueSize = cfg.getInt("queue-size")
    val https = cfg.getBoolean("https")
    val blacklistMinDuration = Duration(cfg.getDuration("blacklist.min-duration", TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS)
    val blacklistMaxDuration = Duration(cfg.getDuration("blacklist.max-duration", TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS)
    val maxRetryTimeout = Duration(cfg.getDuration("max-retry-timeout", TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS)
    val poolSettings = ConnectionPoolSettings(cfg.withFallback(ConfigFactory.load()))
    AkkaHttpClientSettings(
      https,
      hosts,
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
                                   poolSettings: ConnectionPoolSettings,
                                   blacklistMinDuration: FiniteDuration = AkkaHttpClientSettings.default.blacklistMinDuration,
                                   blacklistMaxDuration: FiniteDuration = AkkaHttpClientSettings.default.blacklistMaxDuration,
                                   maxRetryTimeout: FiniteDuration = AkkaHttpClientSettings.default.maxRetryTimeout
                                 ) {
  def hasCredentialsDefined: Boolean = username.isDefined && password.isDefined
}
