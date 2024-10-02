package com.dinotech.elastic4s.akka

import com.typesafe.config.ConfigFactory
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.language.postfixOps

class AkkaHttpClientSettingsTest extends WordSpec with Matchers {


  private val config1 = ConfigFactory.load().getConfig("test1.elastic4s.akka.http.client")
  private val config2 = ConfigFactory.load().getConfig("test2.elastic4s.akka.http.client")
  private val config3 = ConfigFactory.load().getConfig("test3.elastic4s.akka.http.client")
  private val config4 = ConfigFactory.load().getConfig("test4.elastic4s.akka.http.client")
  private val config5 = ConfigFactory.load().getConfig("test5.elastic4s.akka.http.client")


  "AkkaHttpClientSettings" should {

    "get hosts in config file correctly as a string list" in {
      val settings = AkkaHttpClientSettings(config1)
      settings.hosts.headOption shouldBe defined
      settings.hosts.head shouldBe "test:8000"
      settings.https shouldBe true
    }

    "get host in config file correctly as a string" in {
      val settings = AkkaHttpClientSettings(config2)
      settings.hosts.headOption shouldBe defined
      settings.hosts.head shouldBe "test:8000"
      settings.https shouldBe false
    }

    "get username and password in config file correctly" in {
      val settings = AkkaHttpClientSettings(config3)
      settings.hosts.headOption shouldBe defined
      settings.hosts.head shouldBe "test:8000"
      settings.https shouldBe true
      settings.username shouldBe defined
      settings.username.get shouldBe "admin"
      settings.password shouldBe defined
      settings.password.get shouldBe "admin"
    }

    "respect uri protocol over 'https' property" in {
      val settings = AkkaHttpClientSettings(config4)
      settings.hosts.headOption shouldBe defined
      settings.hosts.head shouldBe "test:8000"
      settings.https shouldBe true
      settings.username shouldBe defined
      settings.username.get shouldBe "admin"
      settings.password shouldBe defined
      settings.password.get shouldBe "admin"
      settings.queueSize shouldBe 1000
    }

    "get duration properties correctly" in {
      val settings = AkkaHttpClientSettings(config5)
      settings.hosts.headOption shouldBe defined
      settings.hosts.head shouldBe "test:8000"
      settings.https shouldBe true
      settings.username shouldBe defined
      settings.username.get shouldBe "admin"
      settings.password shouldBe defined
      settings.password.get shouldBe "admin"
      settings.blacklistMinDuration shouldBe 1.minutes
      settings.blacklistMaxDuration shouldBe 30.minutes
      settings.maxRetryTimeout shouldBe 30.seconds
    }

    "get hosts when directly is set it up" in {
      val settings = AkkaHttpClientSettings(List(s"${sys.env.getOrElse("ES_HOST", "127.0.0.1")}:${sys.env.getOrElse("ES_PORT", "9200")}"))
      settings.hosts.headOption shouldBe defined
      settings.hosts.head shouldBe "127.0.0.1:9200"
      settings.https shouldBe false
    }

  }
}
