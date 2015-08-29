package singleThreadedExecution

object GateController extends App {
  println("Testing Gate, hit CTRL-C to exit.")
  val gate = new GateClass
  Users(gate, "Akiko", "Aomori").start()
  Users(gate, "Bob", "Brazil").start()
  Users(gate, "Chie", "Chiba").start()
}


class GateClass {
  var counter: Int = 0
  var name: String = "Nobody"
  var address: String = "Nowhere"
  def pass(name: String, address: String) = synchronized{
    this.counter = counter + 1
    this.name = name
    this.address = address
    check
  }
  def toStfing = synchronized(s"No. $counter : $name, $address")
  def check = {
    if (name.charAt(0) != address.charAt(0)) {
      println("*** BROKEN **** " + toStfing)
    }
  }
}

case class Users(gate: GateClass, myName: String, myAddress: String) extends Thread {
  override def run() {
    println(myName + " BEGIN")
    while (true) {
      gate.pass(myName, myAddress)
    }
  }
}