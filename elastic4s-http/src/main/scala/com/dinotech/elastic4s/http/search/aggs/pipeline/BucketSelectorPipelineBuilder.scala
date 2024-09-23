package com.dinotech.elastic4s.http.search.aggs.pipeline

import com.dinotech.elastic4s.http.ScriptBuilderFn
import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.aggs.pipeline.BucketSelectorPipelineAgg

object BucketSelectorPipelineBuilder {
  def apply(agg: BucketSelectorPipelineAgg): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("bucket_selector")
    builder.startObject("buckets_path")
    agg.bucketsPathMap.foreach(p => builder.field(p._1, p._2))
    builder.endObject()
    builder.rawField("script", ScriptBuilderFn(agg.script))
    builder.endObject()
  }
}
