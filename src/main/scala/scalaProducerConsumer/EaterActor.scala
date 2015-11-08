package scalaProducerConsumer

import akka.actor.{Props, ActorSystem, Actor, ActorLogging}

/**
 * Created by ikawa on 2015/10/25.
 */
class EaterActor extends Actor with ActorLogging{
  val system = ActorSystem("system")
  val table = system.actorOf(Props(classOf[Table], 3))
  def receive = {
    case _ =>
  }
  while(true){
    table ! true
  }
}
