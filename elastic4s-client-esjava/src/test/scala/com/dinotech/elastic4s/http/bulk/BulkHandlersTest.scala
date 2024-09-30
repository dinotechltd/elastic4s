package com.dinotech.elastic4s.http.bulk

import com.dinotech.elastic4s.ElasticApi.RichString
import com.dinotech.elastic4s.bulk.BulkRequest
import com.dinotech.elastic4s.http.ElasticRequest
import com.dinotech.elastic4s.http.HttpEntity.StringEntity
import com.dinotech.elastic4s.http.testutils.StringExtensions.StringOps
import com.dinotech.elastic4s.indexes.IndexRequest
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class BulkHandlersTest extends FlatSpec with BulkHandlers {

  it should "build bulk definition http body" in {
    val request: BulkRequest = BulkRequest(Seq(
      IndexRequest("my_index1" / "_doc", source = Some("""{"field1":"value1"}""")),
      IndexRequest("my_index2" / "_doc", source = Some("""{"field2":"value2"}"""))
    ))

    val expected =
      """{"index":{"_index":"my_index1","_type":"_doc"}}
        |{"field1":"value1"}
        |{"index":{"_index":"my_index2","_type":"_doc"}}
        |{"field2":"value2"}
        |""".stripMargin.withUnixLineEndings

    val esRequest: ElasticRequest = BulkHandler.build(request)
    esRequest.entity should not be empty
    esRequest.entity.get shouldBe a[StringEntity]
    esRequest.entity.get.get shouldBe expected
  }
}
