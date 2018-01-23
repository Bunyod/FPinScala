package problems.geek4geeks

import scala.collection._
object LargestPower {

  case class Memoize[A, B](f: A => B) {
    private[this] val values: mutable.Map[A, B] = mutable.Map.empty
    def apply(x: A) = values getOrElseUpdate(x, f(x))
  }

  def f(a:Int) = {
    Thread.sleep(5000)
    a*a
  }
  def main(args: Array[String]) {
//    val n = scala.io.StdIn.readInt()
//    val p = scala.io.StdIn.readInt()
//
//
//    val s = "asdfA"
//    s.exists(_.isUpper )
//    def largestPower(n: Int, ans: Int): Int = {
//      if (n > 0) {
//        largestPower(n/p, ans + n/p)
//      } else {
//        ans
//      }
//    }
//    println(largestPower(n, 0))

    val b = Memoize(f)
    println(b(10))
    println(b(10))
  }

}