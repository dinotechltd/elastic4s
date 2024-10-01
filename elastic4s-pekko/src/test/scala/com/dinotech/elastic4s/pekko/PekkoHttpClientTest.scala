package com.dinotech.elastic4s.pekko

import com.dinotech.elastic4s.HealthStatus
import com.dinotech.elastic4s.http.ElasticClient
import com.dinotech.elastic4s.testkit.DockerTests
import org.apache.pekko.actor.ActorSystem
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

import scala.util.Try

class PekkoHttpClientTest extends FlatSpec with Matchers with DockerTests with BeforeAndAfterAll {

  private implicit lazy val system = ActorSystem()

  override def beforeAll: Unit = {
    Try {
      client.execute {
        deleteIndex("testindex")
      }.await
    }
  }

  override def afterAll: Unit = {
    Try {
      client.execute {
        deleteIndex("testindex")
      }.await

      pekkoClient.shutdown().await
      system.terminate().await
    }
  }

  private lazy val pekkoClient = PekkoHttpClient(PekkoHttpClientSettings(List("localhost:9200")))

  override val client = ElasticClient(pekkoClient)

  "AkkaHttpClient" should "support utf-8" in {

    client.execute {
      indexInto("testindex" / "testindex").doc("""{ "text":"¡Hola! ¿Qué tal?" }""")
    }.await.result.result shouldBe "created"
  }

  it should "work fine whith _cat endpoints " in {

    client.execute {
      catSegments()
    }.await.result

    client.execute {
      catShards()
    }.await.result

    client.execute {
      catNodes()
    }.await.result

    client.execute {
      catPlugins()
    }.await.result

    client.execute {
      catThreadPool()
    }.await.result

    client.execute {
      catHealth()
    }.await.result

    client.execute {
      catCount()
    }.await.result

    client.execute {
      catMaster()
    }.await.result

    client.execute {
      catAliases()
    }.await.result

    client.execute {
      catIndices()
    }.await.result

    client.execute {
      catIndices(HealthStatus.Green)
    }.await.result

    client.execute {
      catAllocation()
    }.await.result

  }

}

