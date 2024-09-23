package com.dinotech.elastic4s.mappings.dynamictemplate

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.mappings.FieldBuilderFn

object DynamicTemplateBodyFn {

  def build(dyn: DynamicTemplateRequest): XContentBuilder = {

    val builder = XContentFactory.obj()
    builder.startObject(dyn.name)

    dyn.`match`.foreach(builder.field("match", _))
    dyn.unmatch.foreach(builder.field("unmatch", _))
    dyn.pathMatch.foreach(builder.field("path_match", _))
    dyn.pathUnmatch.foreach(builder.field("path_unmatch", _))
    dyn.MatchPattern.foreach(builder.field("match_pattern", _))
    dyn.matchMappingType.foreach(builder.field("match_mapping_type", _))

    builder.rawField("mapping", FieldBuilderFn(dyn.mapping))

    builder.endObject()
  }
}
