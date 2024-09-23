package com.dinotech.elastic4s.http

import com.dinotech.elastic4s.RefreshPolicy

object RefreshPolicyHttpValue {
  def apply(policy: RefreshPolicy): String = policy match {
    case RefreshPolicy.Immediate => "true"
    case RefreshPolicy.WaitFor   => "wait_for"
    case RefreshPolicy.None      => "false"
  }
}
