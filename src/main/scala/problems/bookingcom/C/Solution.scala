package problems.bookingcom.C

object Solution {


  case class Coord(time: Long, isFirst: Boolean)
  def howManyAgentsToAdd(noOfCurrentAgents: Int, callsTimes: Array[Array[Int]]): Int = {

    def makeCoords(calls: Array[Array[Int]], res: List[Coord]): List[Coord] = {
      if (calls.headOption.isDefined) {
        val curr = calls.head
        val coord1 = Coord(curr.head, true)
        val coord2 = Coord(curr.last, false)
        makeCoords(calls.tail, res :+ coord1 :+ coord2)
      } else {
        res
      }
    }

    val sortedCallTimes = makeCoords(callsTimes, Nil).sortWith(sortByTime)
    println(sortedCallTimes)
    0
  }

  val sortByTime =  (c1: Coord, c2: Coord) => {
    val cmp = c1.time - c2.time
    (if (cmp == 0) -c1.isFirst.compareTo(c2.isFirst) else cmp) > 0
  }

  def main(args: Array[String]) {

    val x = scala.io.StdIn.readInt()
    val testCases = scala.io.StdIn.readInt()
    val const = scala.io.StdIn.readInt()
    var a = Array[Array[Int]]()
    (1 to testCases).foreach { _ =>
      val r = scala.io.StdIn.readLine().split(" ").map(_.toInt)
      a = a :+ r
    }
    howManyAgentsToAdd(x, a)
  }
}