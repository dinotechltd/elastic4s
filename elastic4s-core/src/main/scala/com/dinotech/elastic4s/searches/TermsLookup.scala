package com.dinotech.elastic4s.searches

import com.dinotech.elastic4s.DocumentRef

case class TermsLookup(ref: DocumentRef, path: String, routing: Option[String] = None)
