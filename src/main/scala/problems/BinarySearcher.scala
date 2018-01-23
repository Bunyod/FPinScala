

object BinarySearcher {

  def main (args: Array[String]) = {
    val inputData = scala.io.StdIn.readInt()
    val arr = List(48, 1, 2, 36, 3, 4, 37, 6, 27, 9, 56, 74, 35, 64, 15)

    def search(l: Array[Int], value: Int, start: Int, end: Int): Int = {
      if (start >= end) { return -1 }
      val midPoint = start + (end - start) / 2
      if (l(midPoint) == value) {
        midPoint
      } else if (value < l(midPoint)) {
        search(l, value, start, midPoint)
      } else {
        search(l, value, midPoint + 1, end)
      }
    }

    println(search(arr.toArray.sortWith(_ < _), inputData, 0, arr.size))
  }

}