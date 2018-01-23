package problems.bookingcom.B

object Solution {


  def deltaEncode(array: Array[Int]): Array[Int] = {
    def go(array: Array[Int], res: Array[Int], index: Int): Array[Int] = {
      if (index == array.size) {
        res
      } else if (index == 0) {
        go(array, res :+ array(0), index + 1)
      } else {
        val delta = array(index) - array(index - 1)
        if (delta >= -127 && delta <= 127) {
          go(array, res :+ delta, index + 1)
        } else {
          go(array, res :+ -128 :+ delta, index + 1)
        }
      }
    }
    go(array, Array[Int](), 0)
  }

  def main(args: Array[String]) {

    def readNumbers(arr: Array[Int]): Array[Int] = {
      val in = scala.io.StdIn.readLine()
      if (in.nonEmpty) {
        readNumbers(arr :+ in.toInt)
      } else {
        arr
      }

    }
    val numbers = readNumbers(Array[Int]())
    println(deltaEncode(numbers).toList)
  }
}