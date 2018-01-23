//package problems
//
//
//object MergeSort {
//
//  def main(args: Array[String]) {
//
//    val digits = scala.io.StdIn.readInt()
//    val arr = digits.toString.map(_.asDigit).toList
//    mergeSort(arr, List(), 0, arr.length)
//  }
//
//  def merrgeHalves(arr: List[Int], temp: List[Int], leftStart: Int, rightEnd: Int): List[Int] = {
//    val leftEnd = (leftStart + rightEnd) / 2
//    val rightStart = leftEnd + 1
//    val size = rightEnd - leftStart + 1
////    val left = leftStart
////    val right= rightStart
////    val index = leftStart
//     def copier(tempoList: List[Int], left: Int, right: Int, ind: Int): List[Int] = {
//       if (left <= leftEnd && right >= rightEnd) {
//         copier(tempoList :+ arr(ind), left + 1, right, ind + 1)
//       } else {
//         copier(tempoList :+ arr(ind), left, right + 1, ind + 1)
//       }
//     }
//
//    val sortedArr = copier(Nil, leftStart, rightStart, leftStart)
//    var res =
//    System.arraycopy(sortedArr, leftStart, res, left)
//  }
//
//  def mergeSort(arr: List[Int], temp: List[Int], leftStart: Int, rightEnd: Int): List[Int] = {
//    if (leftStart >= rightEnd) {
//      arr
//    } else {
//      val middle = (leftStart + rightEnd) / 2
//      mergeSort(arr, temp, leftStart, middle)
//      mergeSort(arr, temp, middle + 1, rightEnd)
//      merrgeHalves(arr, temp, leftStart, rightEnd)
//    }
//  }
//}