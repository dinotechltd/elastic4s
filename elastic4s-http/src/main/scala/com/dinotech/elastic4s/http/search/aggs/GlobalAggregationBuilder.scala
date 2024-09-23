package com.dinotech.elastic4s.http.search.aggs

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.aggs.GlobalAggregation

object GlobalAggregationBuilder {
  def apply(agg: GlobalAggregation): XContentBuilder = {

    val builder = XContentFactory.obj.startObject("global")

    builder.endObject()

    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
