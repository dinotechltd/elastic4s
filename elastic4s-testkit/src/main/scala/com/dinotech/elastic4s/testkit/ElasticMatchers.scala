package com.dinotech.elastic4s.testkit

trait ElasticMatchers extends SearchMatchers with IndexMatchers

object ElasticMatchers extends ElasticMatchers
