package com.dinotech.elastic4s.http.search.aggs

import com.dinotech.elastic4s.http.ScriptBuilderFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.aggs.MinAggregation

object MinAggregationBuilder {
  def apply(agg: MinAggregation): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("min")
    agg.field.foreach(builder.field("field", _))
    agg.missing.foreach(builder.autofield("missing", _))
    agg.script.foreach { script =>
      builder.rawField("script", ScriptBuilderFn(script))
    }
    builder.endObject()
    builder.endObject()
  }
}
