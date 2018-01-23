
object Comparator {

  case class Coord(time: Long, isFirst: Boolean) extends Ordered[Coord] {
    def compare(that: Coord): Int = (this.time - that.time).toInt
  }

  def main(args: Array[String]): Unit = {

  }
}