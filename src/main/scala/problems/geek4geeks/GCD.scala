package problems.geek4geeks

object GCD {

  def findGCD(a: Int, arr: Array[Int]): Int = {
    if (arr.size == 0) {
      a
    } else {
      val b = arr.head
      val max = math.max(a, b)
      val min = math.min(a, b)
      findGCD(gcd(max, min), arr.tail)
    }
  }

  def gcd(a: Int, b: Int): Int = {
    if (b == 0) {
      a
    } else {
      gcd(b, a % b)
    }
  }
  def main(args: Array[String]): Unit = {
    val a = scala.io.StdIn.readLine().split(" ").map(_.toInt)
    println(findGCD(a.head, a.tail))
  }

}