package com.dinotech.elastic4s.pekko

import scala.concurrent.Future
import scala.concurrent.duration.Duration
import scala.util.Try

import org.apache.pekko.NotUsed
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.model.{HttpRequest, HttpResponse}
import org.apache.pekko.http.scaladsl.settings.ConnectionPoolSettings
import org.apache.pekko.stream.scaladsl.Flow

private[pekko] class DefaultHttpPoolFactory(settings: ConnectionPoolSettings)(
  implicit system: ActorSystem)
  extends HttpPoolFactory {

  private val http = Http()

  private val poolSettings = settings.withResponseEntitySubscriptionTimeout(
    Duration.Inf) // we guarantee to consume consume data from all responses

  override def create[T]()
  : Flow[(HttpRequest, T), (Try[HttpResponse], T), NotUsed] = {
    http.superPool[T](
      settings = poolSettings
    )
  }

  override def shutdown(): Future[Unit] = http.shutdownAllConnectionPools()
}
