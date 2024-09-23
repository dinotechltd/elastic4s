package com.dinotech.elastic4s.searches.queries

trait Query

object NoopQuery extends Query

trait MultiTermQuery extends Query
