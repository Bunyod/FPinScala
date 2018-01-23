package adt

import adt.ADT._
import adt.AdvancedTypeclasses.{ExpressionEvaluator, Json}


object Typeclasses extends App {

  sealed trait Expression
  case class Number(value: Int)  extends Expression
  case class Plus(lhs: Expression, rhs: Expression) extends Expression
  case class Minus(lhs: Expression, rhs: Expression) extends Expression

  implicit val expressionJsonConverter = new Json[Expression] {
    def json(expression: Expression): JsonValue = expression match {
      case Number(value) => JsonNumber(value)
      case Plus(lhs, rhs) => JsonObject(
        Map("op" -> JsonString("+"),
          "lhs" -> json(lhs),
          "rhs" -> json(rhs))
      )
      case Minus(lhs, rhs) => JsonObject(
        Map("op" -> JsonString("-"),
          "lhs" -> json(lhs),
          "rhs" -> json(rhs))
      )
    }
  }

//  object ExpressionEvaluator {
//    def value(expression: Expression): Int = expression match {
//      case Number(value) => value
//      case Plus(lhs, rhs) => value(lhs) + value(rhs)
//      case Minus(lhs, rhs) => value(lhs) - value(rhs)
//    }
//  }

  val expression: Expression = Plus(Number(1), Minus(Number(3), Number(2)))
  println(JsonWriter.write(expression))

  val foo = (1, (2, 3))
  println(JsonWriter.write(foo))
  println(ExpressionEvaluator.evaluate(foo))

}
