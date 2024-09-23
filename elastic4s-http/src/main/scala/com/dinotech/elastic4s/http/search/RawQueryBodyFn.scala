package com.dinotech.elastic4s.http.search

import com.dinotech.elastic4s.json.{XContentBuilder, XContentFactory}
import com.dinotech.elastic4s.searches.queries.RawQuery

object RawQueryBodyFn {
  def apply(q: RawQuery): XContentBuilder = XContentFactory.parse(q.json)
}
