package com.dinotech.elastic4s.http.search.queries.term

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.queries.TypeQuery

object TypeQueryBodyFn {
  def apply(q: TypeQuery): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("type")
    builder.field("value", q.`type`)
    builder.endObject().endObject()
  }
}
