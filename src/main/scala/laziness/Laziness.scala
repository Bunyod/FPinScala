//package laziness
//
//import scala.annotation.tailrec
//
////object Laziness extends App {
////  import Laziness.Stream._
//
//
//sealed trait Stream[+A] {
//  import Stream._
//  def headOption: Option[A] = this match {
//    case Empty => None
//    case Cons(h,  _) => Some(h())
//  }
//
//  def toList: List[A] = {
//    @tailrec
//    def go (s: Stream[A], l: List[A]): List[A] = s match {
//      case Cons(h, t) => go(t(), l :+ h())
//      case _ => l
//    }
//
//    go(this, Nil).reverse
//  }
//
//  def take(n: Int): Stream[A] = this match {
//      case Cons(h, t) if n > 1 => cons(h(), t().take(n - 1))
//      case Cons(h, _) if n == 1 => cons(h(), empty)
//      case _ => empty
//  }
//
//  @tailrec
//  final def drop(n: Int): Stream[A] = this match {
//    case Cons(_, t) if n > 0 => t().drop(n - 1)
//    case _ => this
//  }
//
//  def takeWhile(p: A => Boolean): Stream[A] = this match {
//    case Cons(h, t) if p(h()) => cons(h(), t() takeWhile p)
//    case _ => empty
//  }
//
//  def exists(p: A => Boolean): Boolean = this match {
//    case Cons(h, t) => p(h()) || t().exists(p)
//    case _ => false
//  }
//
//  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
//    case Cons(h,t) => f(h(), t().foldRight(z)(f))
//    case _ => z
//  }
//
//  def exists1(p: A => Boolean): Boolean = foldRight(false)((a, b) => p(a) || b)
//
//  def forAll(p: A => Boolean): Boolean = this match {
//    case Cons(h, t) => !p(h()) || t().forAll(p)
//    case _ => true
//  }
//
//  def forAll1(p: A => Boolean): Boolean = foldRight(true)((a, b) => p(a) && b)
//
//  def takeWhile1(p: A => Boolean): Stream[A] = foldRight(empty[A])((a, b) => if (p(a)) cons(a, b) else empty)
//
//  def headOption1: Option[A] = foldRight(None: Option[A])((a, _) => Some(a))
//
//  def map[B](f: A => B): Stream[B] = foldRight(empty[B])((a, b) => cons(f(a), b))
//
//  def foldRight[B](z: => B)(f: (A, => B) => B): B = // The arrow `=>` in front of the argument type `B` means that the function `f` takes its second argument by name and may choose not to evaluate it.
//    this match {
//      case Cons(h,t) => f(h(), t().foldRight(z)(f)) // If `f` doesn't evaluate its second argument, the recursion never occurs.
//      case _ => z
//    }
//
////  def map[B](f: A => B): Stream[B] =
////    foldRight(empty[B])((h,t) => cons(f(h), t))
//
//  def filter(f: A => Boolean): Stream[A] =
//    foldRight(empty[A])((h,t) =>
//      if (f(h)) cons(h, t)
//      else t)
//
//  def append[B>:A](s: => Stream[B]): Stream[B] =
//    foldRight(s)((h,t) => cons(h,t))
//
//  def flatMap[B](f: A => Stream[B]): Stream[B] =
//    foldRight(empty[B])((h,t) => f(h) append t)
//
//  def append[B>:A](s: => Stream[B]): Stream[B] = foldRight(s)((a, b) => cons(a, b))
////    def flatMap[A,B](as: Stream[A])(f: A => Stream[B]): Stream[B] = foldRight(empty[B])((a, b) => cons(f(a), b))
//
//
//  def mapViaUnfold[B](f: A => B): Stream[B] = unfold(this) {
//    case Cons(h,t) => Some((f(h()), t()))
//    case _ => None
//  }
//
//  def takeViaUnfold(n: Int): Stream[A] = unfold(this, n) {
//    case (Cons(h, t), 1) => Some((h(), (empty, 0)))
//    case (Cons(h, t), n) if n > 1 => Some(h(), (t(), n -1))
//    case _ => None
//  }
//
//  def takeWhileViaUnfold(p: A => Boolean): Stream[A] = unfold(this) {
//    case Cons(h, t) if p(h()) => Some((h(), t()))
//    case _ => None
//  }
//
//  def zipWith[B,C](s2: Stream[B])(f: (A,B) => C): Stream[C] = {
//    unfold((this, s2)) {
//      case (Cons(h1, t1), Cons(h2, t2)) =>
//        Some((f(h1(), h2()), (t1(), t2())))
//
//      case _ => None
//    }
//  }
//
//  def zip[B](s2: Stream[B]): Stream[(A, B)] =
//    zipWith(s2)((_, _))
//
//  def zipAll[B](s2: Stream[B]): Stream[(Option[A], Option[B])] = zipWithAll(s2)((_,_))
//
//  def zipWithAll[B,C](s2: Stream[B])(f: (Option[A], Option[B]) => C): Stream[C] = unfold((this, s2)) {
//    case (Empty, Empty) => None
//    case (Cons(h,t), Empty) => Some(f(Some(h()), Option.empty[B]) -> (t(), empty[B]))
//    case (Empty, Cons(h, t)) => Some(f(Option.empty[A], Some(h())) -> (empty[A] -> t()))
//    case (Cons(h1, t1), Cons(h2, t2)) => Some(f(Some(h1()), Some(h2())) -> (t1() -> t2()))
//  }
//
//  def startsWith[A](s: Stream[A]): Boolean = zipAll(s).takeWhile(!_._2.isEmpty) forAll {
//    case (h, h2) => h == h2
//  }
//
//  def tails: Stream[Stream[A]] = unfold(this) {
//    case Empty => None
//    case c => Some(c, c drop 1)
//  } append Stream(empty)
//
//  def hasSubsequence[A](s: Stream[A]): Boolean =
//    tails exists (_ startsWith s)
//}
//
//case object Empty extends Stream[Nothing]
//case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]
//
//object Stream {
//  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
//    lazy val head = hd
//    lazy val tail = tl
//    Cons(() => head, () => tail)
//  }
//
//  def empty[A]: Stream[A] = Empty
//
//  def apply[A](as: A*): Stream[A] =
//    if (as.isEmpty) empty else cons(as.head, apply(as.tail:_*))
//
//  def constant[A](a: A): Stream[A] = {
//    lazy val tail: Stream[A] = Cons(() => a, () => tail)
//    tail
//  }
//
//  def from(n: Int): Stream[Int] = cons(n, from(n+1))
//
//  val ones: Stream[Int] = Stream.cons(1, ones)
//
//  def fibs = {
//    def go(f0: Int, f1: Int): Stream[Int] = {
//      cons(f0, go(f1, f0 + f1))
//    }
//    go(0, 1)
//  }
//
//  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = {
//    f(z) match {
//      case Some((h, s)) => cons(h, unfold(s)(f))
//      case None => empty
//    }
//  }
//
//  def fibsViaUnfold = unfold((0, 1)) {
//    case (f0, f1) => Some((f0, (f1, f0+f1)))
//  }
//
//  def fromViaUnfold(n: Int) = unfold(n)(n => Some((n, n+1)))
//
//  def constantViaUnfold[A](a: A): Stream[A] = unfold(a)(_ => Some((a, a)))
//
//  def onesViaUnfold = unfold(1)(_ => Some((1,1)))
//
//
//}
//
////  val s = Stream(1,2,3,4)
////  val s: Stream[Int] = empty
////  println(s.takeWhile1(_ % 2 == 1).toList)
////  println(s.headOption1)
////
////  val s1 = Stream(1,1,1,1)
////  println(s1.forAll1(_ == 1))
////
////  val ones: Stream[Int] = Stream.cons(1, ones)
////
////  println(ones.takeWhile1(_ == 1))
//
////  println(s.tails.map(_.toList.reverse).toList.reverse)
//
////  s.hasSubsequence
////}
