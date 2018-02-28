package adt

import cats._
import cats.instances.all._
import cats.syntax.order._

object ADTWithCats extends App {

  sealed trait Day
  case object Monday extends Day
  case object Tuesday extends Day
  case object Wednesday extends Day
  case object Thursday extends Day
  case object Friday extends Day
  case object Saturday extends Day
  case object Sunday extends Day

  val germanOrdering: Order[Day] = new Order[Day] {
    override def compare(x: Day, y: Day): Int = {
      def toInt(d: Day) = d match {
        case Monday => 0
        case Tuesday => 1
        case Wednesday => 2
        case Thursday => 3
        case Friday => 4
        case Saturday => 5
        case Sunday => 6
      }
      toInt(x) compare toInt(y)
    }
  }

  val usOrdering: Order[Day] = new Order[Day] {
    override def compare(x: Day, y: Day): Int = {
      def toInt(d: Day) = d match {
        case Monday => 1
        case Tuesday => 2
        case Wednesday => 3
        case Thursday => 4
        case Friday => 5
        case Saturday => 6
        case Sunday => 0
      }
      toInt(x) compare toInt(y)
    }
  }

  implicit val ordering = germanOrdering // usOrdering

  val d1:Day = Monday
  val d2:Day = Sunday
  println(d1.compare(d2))
}
