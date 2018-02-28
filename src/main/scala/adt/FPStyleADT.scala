package adt

object FPStyleADT {

  sealed trait Shape
  case class Rectangle(width:Double,height:Double)
  case class Circle(radius:Double)

  trait Area[E] {
    def area(elem:E):Double
  }

  implicit val rectangleArea = new Area[Rectangle] {
    override def area(in: Rectangle): Double = in.width * in.height
  }

  def displayArea[I](in:I)(implicit tc:Area[I]) = {
    val ar:Double = tc.area(in)
    println(s"Area: $ar")
  }

  displayArea(Rectangle(10,5))

  object syntax {
    implicit class AreaOps[E](elem:E)(implicit tc:Area[E]) {
      def toArea = tc.area(elem)
    }
  }

  import syntax._

  println(Rectangle(2,2).toArea)

  implicit val circleArea = new Area[Circle] {
    override def area(elem: Circle): Double = Math.PI * elem.radius * elem.radius
  }

  println(Circle(1).toArea)
}
