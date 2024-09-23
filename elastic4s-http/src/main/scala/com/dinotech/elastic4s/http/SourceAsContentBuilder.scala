package com.dinotech.elastic4s.http

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}

object SourceAsContentBuilder {

  def apply(source: Map[String, Any]): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    source.foreach((builder.autofield _).tupled)
    builder
  }
}
