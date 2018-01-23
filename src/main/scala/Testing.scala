object Testing extends App {

  case class Temp(count: Int, prev: Int)

  val input = Seq(1,-1,0,2,2,5,-2)
  val res = input.foldLeft(Temp(0, input.head)){ (a, b) =>
    if ((b < 0 && a.prev >= 0) || (b >= 0 && a.prev < 0) ) {
      a.copy(count = a.count + 1, prev = b)
    } else {
      a.copy(prev = b)
    }
  }

  println(res.count)

}
