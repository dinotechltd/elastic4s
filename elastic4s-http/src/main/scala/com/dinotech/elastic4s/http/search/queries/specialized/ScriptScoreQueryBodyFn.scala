package com.dinotech.elastic4s.http.search.queries.specialized

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.queries.funcscorer.ScriptScore

object ScriptScoreQueryBodyFn {

  def apply(q: ScriptScore): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.rawField("script", q.script.script)
    builder
  }

}
