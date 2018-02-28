package adt

object OOStyleADT extends App {

  trait Area {
    def area:Double
    //def perimeter:Double
  }

  sealed trait Shape extends Area
  case class Rectangle(width:Double,height:Double)
    extends Shape {
    override def area = width * height
  }
  case class Triangle(width:Double, height:Double) extends Shape {
    override def area = 1.0 / 2.0 * width * height
  }

  case class Circle(radius:Double) extends Shape {
    override def area = Math.PI * radius * radius
  }

  println(Triangle(2.0,1.0).area)
  println(Circle(1.0).area)

}
