package com.dinotech.elastic4s.http.search.aggs

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.aggs.ChildrenAggregation

object ChildrenAggregationBuilder {
  def apply(agg: ChildrenAggregation): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder().startObject("children")

    builder.field("type", agg.childType)
    builder.endObject()

    SubAggsBuilderFn(agg, builder)

    builder
  }
}
