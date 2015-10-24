package scalaBalking

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by ikawa on 2015/10/24.
 */
object Balking extends App {
  val data = Data("data.txt")
  val saver = saverFuture(data)
  val changer = changerFuture(data)
  Await.result(changer, Duration.Inf)

  case class Data(filename: String) {
    var content: String = ""
    var changed: Boolean = false

    def change(newContent: String): Unit = {
      synchronized {
        content = newContent
        changed = true
      }
    }

    def save(): Unit = {
      synchronized {
        changed match {
          case true =>
            doSave()
            changed = false
          case _ =>
        }
      }
    }

    private def doSave(): Unit = {
      println(Thread.currentThread().getName + " calls doSave, content =  " + content)
    }
  }


  def saverFuture(data: Data): Future[_] = Future {
    while (true) {
      data.save()
      Thread.sleep(1000)
    }
  }

  def changerFuture(data: Data): Future[_] = Future {
    var i = 0
    while (true) {
      data.change("No. " + i)
      Thread.sleep(Random.nextInt(1000))
      data.save()
      i = i + 1
    }
  }
}

