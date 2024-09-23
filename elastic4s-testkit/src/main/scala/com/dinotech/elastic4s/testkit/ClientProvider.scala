package com.dinotech.elastic4s.testkit

import com.dinotech.elastic4s.http.ElasticClient

trait ClientProvider {
  def client: ElasticClient
}
