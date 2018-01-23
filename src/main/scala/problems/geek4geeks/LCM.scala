package problems.geek4geeks

object LCM {

  def lcm(a: Array[Int]): Int = {
    val elements = a.to[scala.collection.mutable.Seq]
    var lcmOfCollection = 1
    var divisor = 2
    var isContinuing = true
    while (isContinuing) {
      var counter = 0
      var isDivisible = false
      elements.indices.foreach { i =>
        if (elements(i) < 0) {
          elements(i) = elements(i) * (-1)
        }

        if (elements(i) == 0) return 0
        if (elements(i) == 1) counter += 1

        if (elements(i) % divisor == 0) {
          isDivisible = true
          elements(i) = elements(i) / divisor
        }
      }

      if (isDivisible) {
        lcmOfCollection *= divisor
      } else {
        divisor += 1
      }

      if (counter == elements.size) {
        isContinuing = false
      }
    }
    lcmOfCollection
  }

  def main(args: Array[String]) {
    val in = scala.io.StdIn.readLine().split(" ").map(_.toInt)
    println(lcm(in))
  }

}