package com.dinotech.elastic4s.http.search.queries.term

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.queries.PrefixQuery

object PrefixQueryBodyFn {
  def apply(q: PrefixQuery): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder().startObject("prefix")

    builder.startObject(q.field)
    builder.autofield("value", q.prefix)
    q.rewrite.foreach(builder.field("rewrite", _))
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))

    builder.endObject().endObject().endObject()
  }
}
