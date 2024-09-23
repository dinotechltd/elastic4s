package com.dinotech.elastic4s.searches.queries.funcscorer

import com.dinotech.elastic4s.searches.GeoPoint

sealed trait Origin
case class GeoPointOrigin(origin: GeoPoint) extends Origin

case class StringOrigin(origin: String) extends Origin

object Origin {
  implicit def fromString(origin: String): StringOrigin = StringOrigin(origin)
  implicit def fromGeoPoint(origin: GeoPoint): GeoPointOrigin = GeoPointOrigin(origin)
}
