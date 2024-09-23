package com.dinotech.elastic4s.searches.queries

import com.dinotech.elastic4s.DocumentRef

case class PercolateQuery(field: String, `type`: String, ref: Option[DocumentRef] = None, source: Option[String] = None)
    extends Query
