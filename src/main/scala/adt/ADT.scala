package adt

import adt.AdvancedTypeclasses.Json

object ADT {

  //ADT for Json
  sealed trait JsonValue
  case class JsonObject(entries: Map[String, JsonValue]) extends JsonValue
  case class JsonArray(entries: Seq[JsonValue]) extends JsonValue
  case class JsonString(value: String) extends JsonValue
  case class JsonNumber(value: BigDecimal) extends JsonValue
  case class JsonBoolean(value: Boolean) extends JsonValue
  case object JsonNull extends JsonValue

  object JsonWriter {
    def write(jsValue: JsonValue): String = {
      jsValue match {
        case JsonObject(entries) => {
          val serializedEntries =
            for ((key, value) <- entries) yield key + ": " + write(value)
          "{ " + serializedEntries.mkString(", ") + " }"
          }
        case JsonArray(entries) =>
          val serializedEntries = entries map write
          "[ " + serializedEntries.mkString(", ") + " ]"
        case JsonString(value) => "\"" + value + "\""
        case JsonNumber(value) => value.toString()
        case JsonBoolean(value) => value.toString
        case JsonNull => "null"

      }
    }

//    def write[A](value: A)(implicit conv: JsonConverter[A]): String =
//      write(conv.convertToJson(value))

    //equivalent to
    def write[A : Json](value: A): String =  {
      write(implicitly[Json[A]].json(value))
//      val conv = implicitly[Json[A]]
//      write(conv.json(value))
    }
  }

}
