package com.dinotech.elastic4s.http.explain

import com.dinotech.elastic4s.explain.ExplainRequest
import com.dinotech.elastic4s.http.search.queries.QueryBuilderFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}

object ExplainBodyFn {
  def apply(v: ExplainRequest): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.rawField("query", QueryBuilderFn(v.query.get))
    builder.endObject()
  }
}
