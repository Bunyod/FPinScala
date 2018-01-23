package problems.honeypot.B

object Solution {

  @scala.annotation.tailrec
  def toBinaryString(number: Int, binaryStr: String): String = {
    if (number == 0) binaryStr else toBinaryString(number / 2, (number % 2) + binaryStr)
  }

//  case class Counter(positions: List[Int], )
  def main(args: Array[String]) {
    val number = scala.io.StdIn.readInt()
    val binaryString = toBinaryString(number, "")
    val result = binaryString.zipWithIndex.foldLeft(List[Int]()) { (res, s) =>
      if(s._1 == '1') {
        res :+ s._2 + 1
      } else {
        res
      }
    }

    result.size +: result.toArray
    println(result.size)
    result.foreach(println)
  }
}