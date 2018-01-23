package problems.geek4geeks


object Solution {

  case class Xor(zeros: Int = 0, ones: Int = 0)

  def countXOR(n: Int): Int = {
    val xor = toBinaryString(n, Xor())
    xor.ones ^ xor.zeros
  }

  def toBinaryString(n: Int, xor: Xor): Xor = {
    if (n == 0 ) {
      xor
    } else {
      val remainder = n % 2
      val tmpXor = if (remainder == 0) {
        xor.copy(zeros = xor.zeros + 1)
      } else {
        xor.copy(ones = xor.ones + 1)
      }
      toBinaryString(n / 2, tmpXor)
    }
  }

  def main(args: Array[String]) {
    val in = scala.io.StdIn.readInt()
    println(countXOR(in))
  }

}