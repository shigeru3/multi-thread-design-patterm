package scalaProducerConsumer

import akka.actor.{ActorLogging, Actor}

import scala.collection.mutable.ListBuffer

/**
 * Created by ikawa on 2015/10/24.
 */
class Table(val count: Int) extends Actor with ActorLogging {
  var buffer: ListBuffer[String] = ListBuffer()

  def receive = {
    case cake: String => put(cake)
    case _ => take()
  }

  def put(cake: String): Boolean = synchronized{
    println(Thread.currentThread().getName + " puts " + cake)
    while (count >= buffer.length) {
      wait()
    }
    buffer = buffer += cake
    true
  }

  def take(): String = synchronized {
    buffer.toList match {
      case head::tail =>
        buffer.remove(0)
      case _ =>
        ""
    }
  }
}
