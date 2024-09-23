package com.dinotech.elastic4s.searches.aggs.pipeline

object GapPolicy {
  case object INSERT_ZEROS extends GapPolicy
  case object SKIP         extends GapPolicy
}

sealed trait GapPolicy
