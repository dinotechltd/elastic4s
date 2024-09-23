package com.dinotech.elastic4s.http.search.aggs

import com.dinotech.elastic4s.json.XContentBuilder
import com.dinotech.elastic4s.json.XContentFactory
import com.dinotech.elastic4s.searches.aggs.NestedAggregation

object NestedAggregationBuilder {
  def apply(agg: NestedAggregation): XContentBuilder = {
    val builder = XContentFactory.obj().startObject("nested")
    builder.field("path", agg.path)
    builder.endObject()
    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)
    builder
  }
}
