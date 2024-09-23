package com.dinotech.elastic4s.http.count

import com.dinotech.elastic4s.count.CountRequest
import com.dinotech.elastic4s.http.search.queries.QueryBuilderFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}

object CountBodyBuilderFn {
  def apply(request: CountRequest): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    request.query.map(QueryBuilderFn.apply).foreach(builder.rawField("query", _))
    builder
  }
}
