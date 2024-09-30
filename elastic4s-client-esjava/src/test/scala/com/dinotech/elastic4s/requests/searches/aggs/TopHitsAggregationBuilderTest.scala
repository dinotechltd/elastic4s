package com.dinotech.elastic4s.requests.searches.aggs

import com.dinotech.elastic4s.http.search.aggs.TopHitsAggregationBuilder
import com.dinotech.elastic4s.searches.aggs.TopHitsAggregation
import com.dinotech.elastic4s.searches.sort.{FieldSort, SortMode}
import org.scalatest.{FunSuite, Matchers}

class TopHitsAggregationBuilderTest extends FunSuite with Matchers {
  test("top hits aggregation should generate expected json") {
    val q = TopHitsAggregation("top_items")
      .size(5)
      .from(10)
      .version(true)
      .explain(false)
      .sortBy(List(FieldSort("price").sortMode(SortMode.Median)))
    TopHitsAggregationBuilder(q).string() shouldBe
      """{"top_hits":{"size":5,"from":10,"sort":[{"price":{"mode":"median","order":"asc"}}],"explain":false,"version":true}}"""
  }
}
