package scalaProducerConsumer

import java.util.concurrent.TimeUnit

import akka.actor.{Props, ActorSystem, ActorLogging, Actor}
import akka.pattern.ask
import akka.util.Timeout

import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by ikawa on 2015/10/25.
 */
class MakerActor(cook: String) extends Actor with ActorLogging {
  var id = 0
  var cake: Option[String] = None
  val system = ActorSystem("system")
  val table = system.actorOf(Props(classOf[Table], 3))
  implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS)

  makeCake()

  def makeCake()(implicit timeout: Timeout): Unit = {
    cake match {
      case Some(cake) => putCakeToTable
      case _ =>
        Thread.sleep(Random.nextInt(1000))
        cake = Some("[ Cake No." + nextId + " by " + cook + " ]")
        putCakeToTable
    }
  }
  def putCakeToTable(implicit timeout: Timeout) = {
    cake match {
      case Some(cake) =>
        this.cake = None
        (table ? cake).map {
          case true => makeCake()
        }
      case _ => makeCake()
    }
  }
  def receive = {
    case "put" => makeCake
    case "eat" => makeCake
    case "wait" =>
  }
  private def nextId = {
    id = id + 1
    id
  }
}
