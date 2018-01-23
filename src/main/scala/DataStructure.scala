
/**
  * Created by bunyod on 4/16/17.
  */
object DataStructure extends App {

  sealed trait List[+A]
  case object Nil extends List[Nothing]
  case class Cons[+A](head: A, tail: List[A]) extends List[A]

  object List {
    def sum(ints: List[Int]): Int = ints match {
      case Nil => 0
      case Cons(x,xs) => x + sum(xs)
    }

    def product(ds: List[Double]): Double = ds match {
      case Nil => 1.0
      case Cons(0.0, _) => 0.0
      case Cons(x,xs) => x * product(xs)
    }
    def apply[A](as: A*): List[A] =
      if (as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))
  }

  import List._
  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }
  println(x)


  def drop[A](l: List[A], n: Int): List[A] = l match {
    case Nil => Nil
    case Cons(h, t) => if (n == 0) {
      Cons(h, t)
    } else {
      drop(t, n-1)
    }
  }

  val ll = List(1,2,3,4,5)

  println(drop(ll, 2))

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h, t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  println(dropWhile(ll, (x: Int) => x != 4))

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }
  // Cons (1, Cons(2, Cons(3, Cons(4, Cons(5,
  println(append(ll, List(7,8)))


  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }
  }


//  foldRight(Cons(1, Cons(2, Cons(3, Nil))), 0)((x, y) => x + y)
//  1 + foldRight(Cons(2, Cons(3, Nil)), 0)((x, y) => x + y)
//  1 + (2 + foldRight(Cons(3, Nil), 0)((x, y) => x + y))
//  1 + (2 + (3 + foldRight(Nil, 0)((x, y) => x + y)))
//  1 + (2 + (3 + (0)))
//  6

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)(_ + _)

  def product2(ns: List[Int]) =
    foldRight(ns, 1)(_ * _)

  println(sum2(ll))
  println(product2(ll))

  def length[A](l: List[A]) = {
    foldRight(l, 0)((_, acc) => acc + 1)
  }

  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = {
    as match {
      case Nil => z
      case Cons(h, t) => foldLeft(t, f(z, h))(f)
    }
  }


  def lengthLeft[A](l: List[A]) = {
    foldLeft(l, 0)((acc, _) => acc + 1)
  }

  def reverse[A](l: List[A]) = foldLeft(l, List[A]())((nl, h) => Cons(h, nl))

  def foldRightViaFoldLeft[A,B](as: List[A], z: B)(f: (A, B) => B): B = {
    foldLeft(as, (b: B) => b)((g, a) => b => g(f(a, b)))(z)
  }

  def foldLeftViaFoldRight[A,B](as: List[A], z: B)(f: (B, A) => B): B = {
    foldRight(as, (b: B) => b)((a, g) => b => g(f(b, a)))(z)
  }

  def lengthLeft_1[A](l: List[A]) = {
    foldLeftViaFoldRight(l, 0)((acc, _) => acc + 1)
  }
  println("asdfasdf")
  print(lengthLeft_1(ll))
//  def foldRightViaFoldLeft[A,B](l: List[A], z: B)(f: (A,B) => B): B =
//    foldLeft(reverse(l), z)((b,a) => f(a,b))
//
//  def foldRightViaFoldLeft_1[A,B](l: List[A], z: B)(f: (A,B) => B): B =
//    foldLeft(l, (b:B) => b)((g,a) => b => g(f(a,b)))(z)
//
//  def foldLeftViaFoldRight[A,B](l: List[A], z: B)(f: (B,A) => B): B =
//    foldRight(l, (b:B) => b)((a,g) => b => g(f(b,a)))(z)
//  println(lengthLeft(ll))

}
