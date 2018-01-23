import scala.util.Try

/**
  * Created by comp7 on 5/16/17.
  */
object ErrorHandling {

//  sealed trait Option[+A] {
//
//    def map[B](f: A => B): Option[B] = this match {
//      case None => None
//      case Some(a) => Some(f(a))
//    }
//
//    def getOrElse[B>:A](default: => B): B = this match {
//      case None => default
//      case Some(a) => a
//    }
//
//    def flatMap[B](f: A => Option[B]): Option[B] =
//      map(f) getOrElse None
//
//    /*
//    Of course, we can also implement `flatMap` with explicit pattern matching.
//    */
//    def flatMap_1[B](f: A => Option[B]): Option[B] = this match {
//      case None => None
//      case Some(a) => f(a)
//    }
//
//    def orElse[B>:A](ob: => Option[B]): Option[B] =
//      this map (Some(_)) getOrElse ob
//
//    /*
//    Again, we can implement this with explicit pattern matching.
//    */
//    def orElse_1[B>:A](ob: => Option[B]): Option[B] = this match {
//      case None => ob
//      case _ => this
//    }
//
//    def filter(f: A => Boolean): Option[A] = this match {
//      case Some(a) if f(a) => this
//      case _ => None
//    }
//    /*
//    This can also be defined in terms of `flatMap`.
//    */
//    def filter_1(f: A => Boolean): Option[A] =
//      flatMap(a => if (f(a)) Some(a) else None)
//  }
//  case class Some[+A](get: A) extends Option[A]
//  case object None extends Option[Nothing]
//
//  def mean(xs: Seq[Double]): Option[Double] = {
//    if (xs.isEmpty) None
//    else Some(xs.sum / xs.length)
//  }
//
//  def variance(xs: Seq[Double]): Option[Double] = {
//    mean(xs) flatMap(m => mean(xs.map(x => math.pow(x - m, 2))))
//  }
//
//  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
//    a flatMap (aa => b map (bb => f(aa, bb)))

//  sealed trait Either[+E, +A] {
//    def map[B](f: A => B): Either[E, B] = this match {
//      case Right(a) => Right(f(a))
//      case Left(e) => Left(e)
//    }
//
//    def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
//      case Left(e) => Left(e)
//      case Right(a) => f(a)
//    }
//    def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
//      case Left(_) => b
//      case Right(a) => Right(a)
//    }
//    def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
//      for {
//        a <- this
//        b1 <- b
//      } yield f(a,b)
//    }
//
//    def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]]
//
//    def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = as match {
//      case Nil => Right(Nil)
//      case h :: t =>
//    }
//  }
//
//  case class Person(name: Name, age: Age)
//  sealed class Name(val value: String)
//  sealed class Age(val value: Int)
//
//  def mkName(name: String): Either[String, Name] = {
//    if (name == "" || name == null) Left("Name is empty.")
//    else Right(new Name(name))
//  }
//
//  def mkAge(age: Int): Either[String, Age] = {
//    if (age < 0) Left("Age is out of range.")
//    else Right(new Age(age))
//  }
//
//  def mkPerson(name: String, age: Int): Either[String, Person] = {
//    for {
//      name1 <- mkName(name)
//      age1 <- mkAge(age)
//    } yield Person(name1, age1)
//    //    mkName(name).map2(mkAge(age))(Person(_, _))
//  }
//
//  println(mkPerson("", 5))

}



