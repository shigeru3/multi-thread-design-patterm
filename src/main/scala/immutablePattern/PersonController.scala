package immutablePattern

/**
 * Created by ikawa on 2015/08/30.
 */
object PersonController extends App {
  val akiko = new Person("Akiko", "Akita");
  new PrintPersonThread(akiko).start();
  new PrintPersonThread(akiko).start();
  new PrintPersonThread(akiko).start();

  case class Person(name: String, address: String) {
    override def toString = {
      s"[ Person: name = $name, address = $address]"
    }
  }
  case class PrintPersonThread(person: Person) extends Thread {
    override def run() = {
      while(true) {
        println(Thread.currentThread().getName + " prints " + person)
      }
    }
  }
}
