package scalaexercises.intermediate

object Exercises extends App {

  trait PartialType[T[_, _], A] {
    type Apply[B] = T[A, B]
    type Flip[B] = T[B, A]
  }
  trait Fluffy[F[_]] {
    def furry[A, B](f: A => B, fa: F[A]): F[B]
  }
  object Fluffy {
    // Exercise 1
    // Relative Difficulty: 1
    def ListFluffy: Fluffy[List] = {
      new Fluffy[List] {
        override def furry[A, B](f: (A) => B, fa: List[A]) =
          fa.map(f)
      }
    }
    // Exercise 2
    // Relative Difficulty: 1
    def OptionFluffy: Fluffy[Option] = ???
    // Exercise 3
    // Relative Difficulty: 1
    def StreamFluffy: Fluffy[Stream] = ???
    // Exercise 4
    // Relative Difficulty: 1
    def ArrayFluffy: Fluffy[Array] = ???
    // Exercise 5
    // Relative Difficulty: 5
    def Function1Fluffy[X]: Fluffy[PartialType[Function1, X]#Apply] = ???
    // Exercise 6
    // Relative Difficulty: 6
    def EitherLeftFluffy[X]: Fluffy[PartialType[Either.LeftProjection, X]#Flip] = ???
    // Exercise 7
    // Relative Difficulty: 4
    def EitherRightFluffy[X]: Fluffy[PartialType[Either.RightProjection, X]#Apply] =
    ???
  }
  trait Misty[M[_]] extends Fluffy[M] {
    def banana[A, B](f: A => M[B], ma: M[A]): M[B]
    def unicorn[A](a: A): M[A]
    // Exercise 8
    // Relative Difficulty: 3
    // (use banana and/or unicorn)
    def furry[A, B](f: A => B, ma: M[A]): M[B] = ???
  }
  object Misty {
    // Exercise 9
    // Relative Difficulty: 2
    def ListMisty: Misty[List] = ???
    // Exercise 10
    // Relative Difficulty: 2
    def OptionMisty: Misty[Option] = ???
    // Exercise 11
    // Relative Difficulty: 2
    def StreamMisty: Misty[Stream] = ???
    // Exercise 12
    // Relative Difficulty: 2
    def ArrayMisty: Misty[Array] = ???
    // Exercise 13
    // Relative Difficulty: 6
    def Function1Misty[X]: Misty[PartialType[Function1, X]#Apply] =
    ???
    // Exercise 14
    // Relative Difficulty: 7
    def EitherLeftMisty[X]: Misty[PartialType[Either.LeftProjection, X]#Flip] =
    ???
    // Relative Difficulty: 5
    def EitherRightMisty[X]: Misty[PartialType[Either.RightProjection, X]#Apply] =
      ???
    // Exercise 16
    // Relative Difficulty: 3
    def jellybean[M[_], A](ma: M[M[A]], m: Misty[M]): M[A] = ???
    // Exercise 17
    // Relative Difficulty: 6
    def apple[M[_], A, B](ma: M[A], mf: M[A => B], m: Misty[M]): M[B] =
    ???
    // Exercise 18
    // Relative Difficulty: 6
    def moppy[M[_], A, B](as: List[A], f: A => M[B], m: Misty[M]): M[List[B]] =
    ???
  }
  object AdvancedFun {
    case class State[S, A](f: S => (S, A))
    // Exercise 19
    // Relative Difficulty: 9
    def StateFluffy[S]: Fluffy[PartialType[State, S]#Apply] = ???
    // Exercise 20
    // Relative Difficulty: 10
    def StateMisty[S]: Misty[PartialType[State, S]#Apply] = ???
  }
}
