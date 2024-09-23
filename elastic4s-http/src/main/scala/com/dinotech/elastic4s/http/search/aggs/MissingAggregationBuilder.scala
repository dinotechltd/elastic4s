package com.dinotech.elastic4s.http.search.aggs

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.aggs.MissingAggregation

object MissingAggregationBuilder {
  def apply(agg: MissingAggregation): XContentBuilder = {
    val builder = XContentFactory.obj()
    builder.startObject("missing")
    builder.field("field", agg.field.get)
    builder.endObject()
    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
