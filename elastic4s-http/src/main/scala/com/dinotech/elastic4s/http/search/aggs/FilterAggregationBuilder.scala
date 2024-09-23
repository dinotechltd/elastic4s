package com.dinotech.elastic4s.http.search.aggs

import com.dinotech.elastic4s.http.search.queries.QueryBuilderFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.aggs.FilterAggregation

object FilterAggregationBuilder {
  def apply(agg: FilterAggregation): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder()

    builder.rawField("filter", QueryBuilderFn(agg.query))

    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)

    builder
  }
}
