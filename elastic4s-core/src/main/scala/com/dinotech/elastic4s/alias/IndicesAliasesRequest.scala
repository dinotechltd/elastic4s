package com.dinotech.elastic4s.alias

case class IndicesAliasesRequest(actions: Seq[AliasAction])

trait AliasAction
