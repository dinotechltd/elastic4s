package com.dinotech.elastic4s.http.search.queries.specialized

import com.dinotech.elastic4s.json.XContentBuilder
import com.dinotech.elastic4s.searches.queries.funcscorer._

object ScoreFunctionBuilderFn {
  def apply(func: ScoreFunction): XContentBuilder =
    func match {
      case r: RandomScoreFunction   => RandomScoreFunctionBuilderFn(r)
      case g: GaussianDecayScore    => GaussianDecayScoreBuilderFn(g)
      case s: ScriptScore           => ScriptScoreBuilderFn(s)
      case f: FieldValueFactor      => FieldValueFactorBuilderFn(f)
      case e: ExponentialDecayScore => ExponentialDecayScoreBuilderFn(e)
      case w: WeightScore           => WeightBuilderFn(w)
      case l: LinearDecayScore      => LinearDecayScoreBuilderFn(l)
    }
}
