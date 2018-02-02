package xml_parser

import scala.xml.Text
import scala.xml.pull._
import scala.io.Source


object SAXEventParser extends App {

//  val src = Source.fromURL("https://en.wikipedia.org/w/api.php?format=xml&action=opensearch&search=[keyword]")
  val xmlString = Source.fromFile("src/main/resources/test1.fodt")
  val reader = new XMLEventReader(xmlString)

  var isBeginP = false
  var temp = ""
  var tmpPar = ""
  var brokenLine = ""
//  <text:p text:style-name="P30">
//    <text:span text:style-name="T28">Date: </text:span>
//    <text:span text:style-name="T29">
//      <text:s/>&lt;</text:span>
//    <text:span text:style-name="T31">
//      Date when Easy Close Form is fax&apos;d</text:span>
//    <text:span text:style-name="T29">&gt;<text:tab/> </text:span>
//    <text:span text:style-name="T30">CUR</text:span>
//    <text:span text:style-name="T32">R</text:span>
//    <text:span text:style-name="T30">ENT_DATE</text:span>
//  </text:p>

  //      <text: p text:style =" ">.*.* </text:p>

  for(event <- reader) {
    event match {
      case EvElemStart("text", "p", meta, _) =>
        isBeginP = true
        tmpPar = s"<text:p$meta>"
        brokenLine = s"<text:p$meta>"
      case EvElemStart("text", "span", meta, _) =>
        brokenLine += s"<text:span$meta>"
      case EvElemStart(pre, label, meta, _) =>
        if (isBeginP)
          brokenLine = s"<$pre:$label$meta>"
      case EvText(txt) =>
        if(isBeginP) {
          temp += txt
          brokenLine += txt
        }
//      case EvElemEnd("text", "span") =>
//        brokenLine += s"</text:span>"
      case EvElemEnd("text", "p") =>
        isBeginP = false
        tmpPar = tmpPar + temp + "</text:p>"
        brokenLine += "</text:p>"
//        println(tmpPar)
        println(brokenLine)
        temp = ""
        tmpPar = ""
        brokenLine = ""

      case EvElemEnd(pre, label) =>
        if (isBeginP)
          brokenLine += s"</$pre:$label>"
      case EvEntityRef(entity) =>
        if (isBeginP)
          temp += s"&$entity;"
      case EvComment(text) =>
        if (isBeginP)
          println(text)
          brokenLine += text
      case m =>
    }
  }
}
