package testing

/**
  * Created by comp7 on 4/24/17.
  */
object Testing extends App {

//  def doMatch(seq: Seq[Int]): Unit = seq match {
//    case head +: Nil => println(s"head=$head")
//    case head +: tail  => println(s"TAIL=$tail")
//    case _ => print("empty")
//  }
//
//  doMatch(List())
//
//  List(1).grouped(2).toList


  sealed class Name(val value: String)
  sealed class Age(val value: Int)
  case class Person(name: Name, age: Age)

  def mkName(name: String): Either[String, Name] = {
    if (name == "" || name == null) Left("Name is empty.")
    else Right(new Name(name))
  }

  def mkAge(age: Int): Either[String, Age] = {
    if (age < 0) Left("Age is out of range.")
    else Right(new Age(age))
  }

  def mkPerson(name: String, age: Int): Either[String, Person] = {
    for {
      name1 <- mkName(name).right
      age1 <- mkAge(age).right
    } yield Person(name1, age1)
    //    mkName(name).map2(mkAge(age))(Person(_, _))
  }

  println(mkPerson("", 5))

  val l = List(1,2,3,4)

  def appender(l: List[Int], acc: List[List[Int]]): List[List[Int]] = l match {
    case h :: t =>
      appender(t, acc :+ (h :: t))
    case Nil => acc
  }

  println(appender(l, Nil))

}
