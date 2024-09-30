package com.dinotech.elastic4s.requests.searches.aggs

import com.dinotech.elastic4s.http.search.aggs.SigTermsAggregationBuilder
import org.scalatest.{FunSuite, Matchers}

class SigTermsAggregationBuilderTest extends FunSuite with Matchers{

  import com.dinotech.elastic4s.http.ElasticDsl._

  test("sig terms aggregation with 'field' and 'background_filter' should generate expected json") {
    val agg = sigTermsAggregation("name")
      .field("field")
      .backgroundFilter(termQuery("text", "test"))

    SigTermsAggregationBuilder(agg).string() shouldBe
      """{"significant_terms":{"field":"field","background_filter":{"term":{"text":{"value":"test"}}}}}"""
  }


}
