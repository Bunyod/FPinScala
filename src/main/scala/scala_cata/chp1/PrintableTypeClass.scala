package scala_cata.chp1

import java.util.Date

/*======== EX: 1,2,3 ========*/

trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val stringPrinter = new Printable[String] {
    def format(input: String): String = input
  }

  implicit val intPrinter = new Printable[Int] {
    def format(input: Int): String = input.toString
  }

  implicit val catPrintable = new Printable[Cat] {
    def format(cat: Cat): String = {
      val name = Printable.format(cat.name)
      val age = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }

  implicit val datePrintable = new Printable[Date] {
    def format(value: Date): String = value.toString
  }
}

object Printable {
  def format[A](input: A)(implicit printer: Printable[A]): String = printer.format(input)
  def print[A](input: A)(implicit printer: Printable[A]): Unit = println(printer.format(input))
}

final case class Cat(
  name: String,
  age: Int,
  color: String
)

object PrintableSyntax {
  implicit class PrintOps[A](input: A) {
    def format(implicit printable: Printable[A]): String = printable.format(input)
    def print(implicit printable: Printable[A]): Unit = println(printable.format(input))
  }
}