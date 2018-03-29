package scala_cata.chp1

import java.util.Date

object CatsTypeClasses extends App {


  import cats.Show
  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.show._

//  val showInt = Show.apply[Int]
//  val showString: Show[String] = Show.apply[String]

  val shownInt = 123.show
  val shownString = "abc".show
  println(shownInt == "123")
  println(shownString)

  implicit val dateShow: Show[Date] = Show.show(date => s"${date.getTime}ms since the epoch")

  println(new Date().show)

  implicit val catShow: Show[Cat] = Show.show { cat =>
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show
    s"$name is a $age year-old $color cat."
  }

  println(Cat("Garfield", 35, "ginger and black").show)

}
