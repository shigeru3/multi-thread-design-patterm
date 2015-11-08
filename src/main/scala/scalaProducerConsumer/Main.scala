package scalaProducerConsumer

import akka.actor.{Props, ActorSystem}

/**
 * Created by ikawa on 2015/10/25.
 */
object Main extends App {
  val system = ActorSystem("system")

  val sakai = system.actorOf(Props(classOf[MakerActor], "sakai"))
  val michiba = system.actorOf(Props(classOf[MakerActor], "michiba"))
  val chin = system.actorOf(Props(classOf[MakerActor], "chin"))
  val shigeru = system.actorOf(Props(classOf[EaterActor]))
  val masayuki = system.actorOf(Props(classOf[EaterActor]))
  val tomoaki = system.actorOf(Props(classOf[EaterActor]))
}
