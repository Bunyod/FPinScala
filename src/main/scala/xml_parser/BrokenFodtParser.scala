package xml_parser

import scala.annotation.tailrec
import scala.io.Source

object BrokenFodtParser extends App {

  object CombinatorialOps {
    implicit class CombinatorialList[A](l: List[A]) {

      def xcombinations(n: Int): List[List[A]] =
      if (n > l.size) Nil
      else l match {
        case _ :: _ if n == 1 =>
          l.map(List(_))
        case hd :: tl =>
          tl.xcombinations(n - 1).map(hd :: _) ::: tl.xcombinations(n)
        case _ => Nil
      }
      def xsubsets: List[List[A]] =
        (2 to l.size).foldLeft(l.xcombinations(1))((a, i) => l.xcombinations(i) ::: a)
    }
  }

  import CombinatorialOps._
//  val s = "LAST_NAME".split("").toList.xsubsets
//  val j = "LAST_NAME".split("").toList
//  s.foreach { r =>
//    val diffByInd = j.zipWithIndex diff r.zipWithIndex
//    val diffByValue = j diff r
//    if (diffByValue.nonEmpty) {
//      val realDiff = diffByInd.filter(n => diffByValue.contains(n._1))
//      println(makeRegExStr("LAST_NAME", realDiff.map(_._2)))
//    }
//  }

  def generatePossibleRegExps(placeholders: Seq[String]): Seq[String] = {
    placeholders.flatMap { placeholder =>
      val subsets = placeholder.split("").toList.xsubsets
      val plInArray = placeholder.split("").toList
      subsets.map { letters =>
        val diffByInd = plInArray.zipWithIndex diff letters.zipWithIndex
        val diffByValue = plInArray diff letters
        if (diffByValue.nonEmpty) {
          val realDiff = diffByInd.filter(n => diffByValue.contains(n._1))
          makeRegExStr(placeholder, realDiff.map(_._2))
        } else {
          placeholder
        }
      }
    }
  }

  @tailrec
  def findFirst(str: String, sym: Char, res: String): String = {
    if (str.isEmpty) res
    else if (str.head != sym) findFirst(str.tail, sym, res + str.head) else res
  }

  def makeRegExStr(originalStr: String, diff: List[Int]): String = {
    @tailrec
    def go(originalStr: String, prev: Int, diff: List[Int], rem: String, res: String): String = diff match {
      case h :: tail =>
        val tmp = originalStr.substring(prev, h)
        val rem = originalStr.substring(h + 1, originalStr.length)
        go(originalStr, h + 1, tail, rem, res + tmp + ".*" + originalStr.charAt(h) + ".*.?")
      case Nil => res + rem
    }
    go(originalStr, 0, diff, "", "")
  }

//  val FilePlaceholders = Seq("FIRST_NAME", "LAST_NAME", "FULL_NAME", "CUSTOMER_PHONE", "CUSTOMER_EMAIL", "CURRENT_DATE")
  val FilePlaceholders = Seq("CURRENT_DATE")
  val lines = Source.fromFile("src/main/resources/test1.fodt").mkString

  def findAndReplace(str: String) = {
    val regexps = generatePossibleRegExps(FilePlaceholders).filter(_.lengthCompare(10)<0) //reduce possiblity
    regexps.foreach { rawRegexp =>

      val regexp = s"(?s)($rawRegexp)".r
//    val regexp = "CUR.*<text:.*.>R.*[<|</].*>ENT_DATE.*.+?</text:p>)".r
//    val regexp = "(<text:span.*?>CURR.*{1,20}.+?ENT_DATE)".r
//    val regexp = "(CUR.*.?ENT_DATE)".r
//    val regexp = "(CUR.*R.*.?ENT_DATE)".r
    println(regexp)
    val row = regexp.findAllMatchIn(str).toList
    println(row)
    }
  }
  var replaceableString = findAndReplace(lines)

}
