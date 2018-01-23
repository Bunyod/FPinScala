import scala.annotation.tailrec

/**
  * Created by bunyod on 4/16/17.
  */
object Polymorphic extends App {

  def findFirst[A](as: Array[A], p: A => Boolean): Int = {
    @tailrec
    def loop(n: Int): Int = {
      if (n >= as.length) -1
      else if (p(as(n))) n
      else loop(n + 1)
    }
    loop(0)
  }

  val arr = Array(1,2,3)
  val arr1 = Array("1", "asdf", "qwer")
  println(findFirst(arr1, (x: String) => x == "qwer"))
  println(findFirst(arr, (x: Int) => x == 5))


  def partial1[A, B, C](a: A, f: (A, B) => C): B => C = {
    b => f(a, b)
  }

  def currying[A, B, C](f: (A, B) => C): A => (B=>C) = {
    a => b => f(a, b)
  }

  def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
    (a, b) => f(a)(b)
  }

  def compose[A, B, C](f : B => C, g: A => B): A => C = {
    a => f(g(a))
  }

  val f = (x: Double) => math.Pi/2 - x
  val cos = f andThen math.sin

}
