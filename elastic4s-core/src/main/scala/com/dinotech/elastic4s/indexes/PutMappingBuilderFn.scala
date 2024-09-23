package com.dinotech.elastic4s.indexes

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.mappings.{MappingBuilderFn, PutMappingRequest}

object PutMappingBuilderFn {

  def apply(pm: PutMappingRequest): XContentBuilder =
    pm.rawSource match {
      case None         => MappingBuilderFn.build(pm)
      case Some(source) => XContentFactory.parse(source)
    }
}
