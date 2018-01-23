package problems.honeypot.A

object Solution {

  def isTwins(s1: String, s2: String): Boolean = {
    val evens1 = s1.zipWithIndex.filter(_._2 % 2 == 0).sortBy(_._1).map(_._1)
    val odds1 = s1.zipWithIndex.filter(_._2 % 2 == 1).sortBy(_._1).map(_._1)
    val evens2 = s2.zipWithIndex.filter(_._2 % 2 == 0).sortBy(_._1).map(_._1)
    val odds2 = s2.zipWithIndex.filter(_._2 % 2 == 1).sortBy(_._1).map(_._1)
    if (evens1 == evens2 && odds1 == odds2) {
      true
    } else {
      false
    }
  }
  def twins(a: Array[String], b: Array[String]): Array[String] = {
    val result =(0 until a.size).map { i =>
      if (isTwins(a(i), b(i))) {
        "Yes"
      } else {
        "No"
      }
    }
    result.toArray
  }

  def main(args: Array[String]) {
    val na = scala.io.StdIn.readInt()
    var s1 = Array[String]()
    var s2 = Array[String]()
    (1 to na).foreach {_ =>
      s1 = s1 :+ scala.io.StdIn.readLine()
    }
    val na1 = scala.io.StdIn.readInt()
    (1 to na1).foreach {_ =>
      s2 = s2 :+ scala.io.StdIn.readLine()
    }
    println(twins(s1, s2).toList)
  }
}