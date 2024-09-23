package com.dinotech.elastic4s

case class FetchSourceContext(fetchSource: Boolean,
                              includes: Array[String] = Array.empty,
                              excludes: Array[String] = Array.empty)
