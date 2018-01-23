package problems.bookingcom.A


object Solution {

  def polygon(a: Int, b: Int, c: Int, d: Int): Int = {
    if (a <= 0 || b <= 0 || c <=0 || d <= 0) {
      0
    } else if (a == b && b == c && c == d) {
      2
    } else if (a == c && b == d) {
      1
    } else {
      0
    }
  }
  def main(args: Array[String]) {
    val a = scala.io.StdIn.readInt()
    val b = scala.io.StdIn.readInt()
    val c = scala.io.StdIn.readInt()
    val d = scala.io.StdIn.readInt()
    println(polygon(a, b, c, d))
  }
}