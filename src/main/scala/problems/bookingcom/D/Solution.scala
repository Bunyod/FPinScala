package problems.bookingcom.D

object Solution {

  def main(args: Array[String]) {

    val digits = scala.io.StdIn.readInt()
    val numArr = digits.toString.map(_.asDigit)
    numArr.sortWith(_ > _).foreach(print)
  }
}