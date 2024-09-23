package com.dinotech.elastic4s.http.index

import com.fasterxml.jackson.annotation.JsonProperty
import com.dinotech.elastic4s.DocumentRef
import com.dinotech.elastic4s.http.Shards

case class IndexResponse(@JsonProperty("_id") id: String,
                         @JsonProperty("_index") index: String,
                         @JsonProperty("_type") `type`: String,
                         @JsonProperty("_version") version: Long,
                         @JsonProperty("_seq_no") seqNo: Long,
                         @JsonProperty("_primary_term") primaryTerm: Long,
                         result: String,
                         @JsonProperty("forced_refresh") forcedRefresh: Boolean,
                         shards: Shards) {
  def ref = DocumentRef(index, `type`, id)
}
