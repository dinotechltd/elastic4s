package com.dinotech.elastic4s.searches.queries.funcscorer

import com.dinotech.elastic4s.searches.queries.Query

trait ScoreFunction {
  def filter: Option[Query]
}
