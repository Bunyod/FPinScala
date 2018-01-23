package problems.codolity.B

object Solution {



  def main(args: Array[String]) {
    val input = scala.io.StdIn.readLine()
    val prefixes = collection.mutable.Seq.fill(input.length)(0)
    (1 until input.length).foreach { ind =>
      var j = prefixes(ind - 1)
      while (j > 0 && input(ind) != input(j)) {
        j = prefixes(j - 1)
      }
      if (input(ind) == input(j)) {
        j = j + 1
      }
      prefixes(ind)= j
    }
    prefixes.last
  }
}