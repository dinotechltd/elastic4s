package com.dinotech.elastic4s.http.search.queries.term

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.queries.ExistsQuery

object ExistsQueryBodyFn {
  def apply(q: ExistsQuery): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("exists")
    builder.field("field", q.field)
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))
    builder.endObject()
    builder.endObject()
    builder
  }
}
