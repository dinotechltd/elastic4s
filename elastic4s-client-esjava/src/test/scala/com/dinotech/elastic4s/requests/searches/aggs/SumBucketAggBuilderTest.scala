package com.dinotech.elastic4s.requests.searches.aggs

import com.dinotech.elastic4s.http.search.SearchBodyBuilderFn
import com.dinotech.elastic4s.searches.{DateHistogramInterval, SearchRequest}
import org.scalatest.{FunSuite, Matchers}

class SumBucketAggBuilderTest extends FunSuite with Matchers {

  import com.dinotech.elastic4s.http.ElasticDsl._

  test("sum bucket agg should match the spec") {
    val search = SearchRequest("myindex").aggs(
      dateHistogramAgg("sales_per_month", "date").interval(DateHistogramInterval.Month).addSubagg(
        sumAgg("sales", "price")
      ),
      sumBucketAggregation("sum_monthly_sales", "sales_per_month>sales")
    )
    SearchBodyBuilderFn(search).string() shouldBe
      """{"aggs":{"sales_per_month":{"date_histogram":{"interval":"1M","field":"date"},"aggs":{"sales":{"sum":{"field":"price"}}}},"sum_monthly_sales":{"sum_bucket":{"buckets_path":"sales_per_month>sales"}}}}"""
  }
}
