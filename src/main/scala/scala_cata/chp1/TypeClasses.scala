package scala_cata.chp1

import java.util.Date

object TypeClasses extends App {

  import JsonWriterInstance._
  val personJs = Json.toJson(Person("Dave", "dave@example.com"))
  println(personJs)

  import JsonSyntax._
  println(Person("Dave", "dave@example.com").toJson)

  import PrintableInstances._
  val cat = Cat("Mosh", 7, "ginger and black")
//  Printable.print(cat)

  import PrintableSyntax._
  cat.print
  new Date().print

  import cats.Show
  import cats.instances.int._
  val showInt = Show.apply[Int]
}
