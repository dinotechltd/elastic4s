package com.dinotech.elastic4s.http.search.template

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.TemplateSearchRequest

object TemplateSearchBuilderFn {
  def apply(req: TemplateSearchRequest): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.field("id", req.name)
    if (req.params.nonEmpty) {
      builder.startObject("params")
      req.params.foreach { case (key, value) => builder.autofield(key, value) }
      builder.endObject()
    }
    builder.endObject()
  }
}
