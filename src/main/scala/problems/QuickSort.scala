package problems

object QuickSort {

  def quickSort(arr: Array[Int]): Array[Int] = {
    if (arr.length < 2) arr
    else {
      val pivot = arr(arr.length / 2)
      quickSort(arr.filter(pivot > _)) ++ arr.filter(pivot == _) ++
        quickSort(arr.filter(pivot < _))
    }
  }

  def main(args: Array[String]) {

    val digits = scala.io.StdIn.readLine().split(" ").map(_.toInt)
    println(quickSort(digits).toList)
  }

}