//package monads
//
////import parallelism.Par
//import parallelism.Par
//import parallelism.Par._
//import parsing.Parsers
//import parsing.instances.Reference
//import parsing.instances.ReferenceTypes.Parser
//import testing.Gen
//import laziness.Stream
///**
//  * Created by bunyod on 7/24/17.
//  */
//
//trait Functor[F[_]] {
//  def map[A,B](fa: F[A])(f:A => B): F[B]
//  def distribute[A,B](fab: F[(A, B)]): (F[A], F[B]) = (map(fab)(_._1), map(fab)(_._2))
//  def codistribute[A,B](e: Either[F[A], F[B]]): F[Either[A, B]] = e match {
//    case Left(fa) =>
//      map(fa)(Left(_))
//    case Right(fb) =>
//      map(fb)(Right(_))
//  }
//
//}
//
//object Functor {
//
//  val listFunctor = new Functor[List] {
//    def map[A,B](fa: List[A])(f:A => B): List[B] = fa map f
//  }
//
//}
//
//trait Monad[F[_]] extends Functor[F] {
////  def map[A,B](fa: F[A])(f: A => B): F[B]
////  def flatMap[A,B](fa: F[A])(f: A => F[B]): F[B]
//  def unit[A](a: => A): F[A]
//  def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]
//  def map[A, B](ma: F[A])(f: A => B): F[B] = flatMap(ma)(a => unit(f(a)))
//
////  def map2[A,B,C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] = fa flatMap ( a => fb.map ( b => f(a, b) ))
//  def map2[A,B,C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] = flatMap(fa)(a => map(fb)(b => f(a, b)))
//
//}
//
//object Monad {
//  val genMonad = new Monad[Gen] {
//  override def unit[A](a: => A): Gen[A] = Gen.unit(a)
//  override def flatMap[A, B](ma: Gen[A])(f: (A) => Gen[B]): Gen[B] = ma flatMap f
//  }
//
//  val parMonad = new Monad[Par] {
//    override def unit[A](a: => A): Par[A] = Par.unit(a)
//    override def flatMap[A, B](ma: Par[A])(f: (A) => Par[B]): Par[B] = Par.flatMap(ma)(f)
//  }
//
//  val optionMonad = new Monad[Option] {
//    override def unit[A](a: => A): Option[A] = Some(a)
//    override def flatMap[A, B](ma: Option[A])(f: (A) => Option[B]): Option[B] = ma flatMap f
//  }
//
//  def parserMonad[P[+_]](p: Parsers[P]) = new Monad[P] {
//
//    override def unit[A](a: => A) = p.succeed(a)
//    override def flatMap[A, B](ma: P[A])(f: (A) => P[B]): P[B] = {
//      p.flatMap(ma)(f)
//    }
//  }
//
//  val streamMonad = new Monad[Stream] {
//    override def unit[A](a: => A): Stream[A] = Stream.constant(a)
//    override def flatMap[A, B](ma: Stream[A])(f: (A) => Stream[B]): Stream[B] = ma flatMap f
//  }
//
//  val listMonad = new Monad[List] {
//    override def unit[A](a: => A): List[A] = List(a)
//    override def flatMap[A, B](ma: List[A])(f: (A) => List[B]): List[B] = ma flatMap f
//  }
//}
