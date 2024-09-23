package com.dinotech.elastic4s.indexes

import com.dinotech.elastic4s.analyzers._
import com.dinotech.elastic4s.mappings.MappingDefinition

trait CreateIndexApi {

  def createIndex(name: String): CreateIndexRequest = CreateIndexRequest(name)

  def analyzers(analyzers: AnalyzerDefinition*) = new AnalyzersWrapper(analyzers)
  def tokenizers(tokenizers: Tokenizer*)        = new TokenizersWrapper(tokenizers)
  def filters(filters: TokenFilter*)            = new TokenFiltersWrapper(filters)

  def mapping(name: String): MappingDefinition = MappingDefinition(name)

  class AnalyzersWrapper(val analyzers: Iterable[AnalyzerDefinition])
  class TokenizersWrapper(val tokenizers: Iterable[Tokenizer])
  class TokenFiltersWrapper(val filters: Iterable[TokenFilter])
}
