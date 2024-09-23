package com.dinotech.elastic4s.monix.instances

import com.dinotech.elastic4s.http.Functor
import com.dinotech.elastic4s.monix.TaskExecutor
import monix.eval.Task

trait TaskInstances {
  implicit val taskFunctor: Functor[Task] = new Functor[Task] {
    override def map[A, B](fa: Task[A])(f: A => B): Task[B] = fa.map(f)
  }

  implicit val taskExecutor: TaskExecutor = new TaskExecutor
}
