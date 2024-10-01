package com.dinotech.elastic4s.pekko

import scala.concurrent.Future
import scala.util.Try

import org.apache.pekko.NotUsed
import org.apache.pekko.http.scaladsl.model.{HttpRequest, HttpResponse}
import org.apache.pekko.stream.scaladsl.Flow

/**
  * Factory for Akka's http pool flow.
  */
private[pekko] trait HttpPoolFactory {

  def create[T](): Flow[(HttpRequest, T), (Try[HttpResponse], T), NotUsed]

  def shutdown(): Future[Unit]
}
