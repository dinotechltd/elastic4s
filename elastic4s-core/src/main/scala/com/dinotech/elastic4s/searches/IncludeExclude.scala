package com.dinotech.elastic4s.searches

case class IncludeExclude(include: Seq[String], exclude: Seq[String])

case class IncludePartition(partition: Int, numPartitions: Int)
