package guardedSuspension

import java.util

import scala.concurrent.Future
import scala.util.Random

/**
 * Created by ikawa on 2015/08/30.
 */
object RequestController extends App {
  val requestQueue = new RequestQueue
  ClientThread(new Random, "akiko", requestQueue)
  ServerThread(new Random, "shigeru", requestQueue)

  case class Request(name: String) {
    override def toString = s"[ Request $name ]"
  }
  class RequestQueue() {
    private val queue: util.Queue[Request] = new util.LinkedList[Request]()
    def getRequest: Request = {
      while (queue.peek() == null) {
        try {
          wait()
        } catch {
          case e: InterruptedException =>
        }
      }
      queue.remove()
    }
    def putRequest(request: Request) = {
      queue.offer(request)
    }
  }
  case class ClientThread(random: Random, name: String, requestQueue: RequestQueue) extends Thread {
    override def run() = {
      (0 to 10000).foreach{ i =>
        val request = Request("No." + i)
        println(Thread.currentThread().getName + " request " + request)
        requestQueue.putRequest(request)
        Thread.sleep(random.nextInt(1000))
      }
    }
  }
  case class ServerThread(random: Random, name: String, requestQueue: RequestQueue) extends Thread {
    override def run() = {
      (0 to 10000).foreach{i =>
        val request = requestQueue.getRequest
        println(Thread.currentThread().getName + " handles " + request)
        Thread.sleep(random.nextInt(1000))
      }
    }
  }
}
