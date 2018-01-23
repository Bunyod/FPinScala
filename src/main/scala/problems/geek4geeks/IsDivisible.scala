

object IsDivisible {

  def isDivisibleBy3(s: String): String = {
    val sumDigits = s.foldLeft(0)((acc, n) => acc + n.asDigit)
    if (sumDigits % 3 == 0) {
      "Yes"
    } else {
      "No"
    }
  }

  def isDivisibleBy999(s: String, acc: List[Int]): String = {
    if (s.length == 0) {
      if (acc.sum % 999 == 0) {
        "Yes"
      } else {
        "No"
      }
    } else {
      val lastThreeDigits = s.takeRight(3)
      isDivisibleBy999(s.dropRight(3), acc :+ lastThreeDigits.toInt)
    }
  }

  def main(args: Array[String]) {
    val in = scala.io.StdIn.readLine()
//    println(isDivisibleBy3(in))
    println(isDivisibleBy999(in, Nil))

  }
}