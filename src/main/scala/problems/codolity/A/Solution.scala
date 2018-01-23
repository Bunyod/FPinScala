package problems.codolity.A


object Solution {

  def factorial(n: Int): Int = {
    if (n == 0) {
     1
    }
    else {
     n * factorial(n-1)
    }
  }

  def main(args: Array[String]) {
    val input = scala.io.StdIn.readLine()
    val ans = solution(input.toInt)
    println(ans)
  }

  def solution(n: Int): Int = {
    if (n == 0) {
      1
    } else {

      val input = n.toString
      val counter = collection.mutable.Seq.fill(10)(0)
      input.foreach { c =>
        counter(c - '0') = counter(c - '0') + 1
      }
      @scala.annotation.tailrec
      def calcRes(res: Int, cnt: Int): Int = {
        if ( cnt > 9) {
          res
        } else {
          calcRes(res/factorial(counter(cnt)), cnt + 1)
        }
      }
      (1 to 9).foldLeft(0) { (ind, ans) =>
        if (counter(ind) > 0) {
          counter(ind) = counter(ind) - 1
          val res =  calcRes(factorial(input.length - 1), 0)
          counter(ind) = counter(ind) + 1
          ans + res
        } else {
          ans
        }
      }

    }

  }

}