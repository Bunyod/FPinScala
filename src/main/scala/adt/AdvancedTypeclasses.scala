package adt

import adt.ADT.{JsonNumber, JsonObject, JsonValue}

object AdvancedTypeclasses {

  sealed trait Expression[A] {
    def value(expr: A): Int
  }

  object ExpressionEvaluator {
    def evaluate[A : Expression](expr: A): Int =
      implicitly[Expression[A]].value(expr)
  }

  object Expression {

    implicit val intExpression = new Expression[Int] {
      def value(n: Int): Int = n
    }

    implicit def pairPlusExpression[T1 : Expression, T2 : Expression] = new Expression[(T1, T2)] {
      override def value(pair: (T1, T2)): Int =
        implicitly[Expression[T1]].value(pair._1) +
        implicitly[Expression[T2]].value(pair._2)
    }
  }

  trait Json[A] {
    def json(value: A): JsonValue
  }

  object Json {

    implicit val intJson = new Json[Int] {
      def json(value: Int): JsonValue = JsonNumber(value)
    }

    implicit def pairJson[T1 : Json, T2 : Json] = new Json[(T1, T2)] {
      def json(pair: (T1, T2)): JsonValue = JsonObject(
        Map(
          "fst" -> implicitly[Json[T1]].json(pair._1),
          "snd" -> implicitly[Json[T2]].json(pair._2)
        ))
    }
  }

}
