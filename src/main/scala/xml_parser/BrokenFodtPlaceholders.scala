package xml_parser

import scala.io.Source

object BrokenFodtPlaceholders extends App {

  val lines = Source.fromFile("src/main/resources/test1.fodt").mkString

  var s = lines
  val pattern0 = ">(.*?)\\s*%\\s*(<.*?>)*(.*?)%\\s*<".r
  val pattern3 = "<.*?>".r
  val ss = pattern0.findAllMatchIn(s).foldLeft(s) { (acc, matches) =>
    val line = matches.toString()
    val begin = Option(matches.group(1)).getOrElse("")
    val labels = Option(matches.group(2)).getOrElse("")
    val text = Option(matches.group(3)).getOrElse("")
    val linePattern = pattern3.findAllMatchIn(text).toList
    val placeholder = linePattern.foldLeft(text) {
      (res, t) => res.replace(t.toString(), "")
    }.replace(" ", "")
    acc.replace(line, s">$labels${linePattern.mkString}$begin %$placeholder%<")
  }

  println(ss)


}
