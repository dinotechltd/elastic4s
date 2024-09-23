package com.dinotech.elastic4s.searches.collapse

trait CollapseApi {
  def collapseField(field: String): CollapseRequest = CollapseRequest(field)
}
