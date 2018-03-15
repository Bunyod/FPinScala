import datastructures.stack.threadsafe.TSStack

import scala.util.Random

object TestTask extends App {

  val stack = new TSStack[Int]()

  /* producer thread */
  new Thread() {
    override def run(): Unit = {
      val random = new Random()
      /* bounded loops, since the analyzer actually runs this code */
      (1 to 10).foreach { _ =>
        stack.push(random.nextInt(100))
      }
    }
  }.start()

  /* consumer thread */
  new Thread() {
    override def run(): Unit = {
      (1 to 5).foreach { _ =>
        println(stack.pop())
      }
    }
  }.start()

//  run()
//  Thread.sleep(5000)
//  println(stack.pop())
}
