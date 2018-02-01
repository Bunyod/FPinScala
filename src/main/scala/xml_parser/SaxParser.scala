package xml_parser

import java.io.StringReader

import org.w3c.dom._
import javax.xml.parsers._

import org.xml.sax.InputSource

import scala.io.Source

object SaxParser extends App {

  val factory = DocumentBuilderFactory.newInstance()
  val builder = factory.newDocumentBuilder()
  val xmlString = Source.fromFile("src/main/resources/test1.fodt").mkString
  val doc = builder.parse(new InputSource(new StringReader(xmlString)))


  def path: Node => String = {
    case document: Document => ""
    case node => path(node.getParentNode) + "/" + node.getNodeName
  }
  def pathElement(element: Element) = {
    def sameName(f: Node => Node)(n: Node) =
      Stream.iterate(n)(f).tail.takeWhile(_ != null).filter(
        _.getNodeName == n.getNodeName
      ).toList
    val preceding = sameName(_.getPreviousSibling) _
    val following = sameName(_.getNextSibling) _
    "/" + Stream.iterate[Node](element)(_.getParentNode).map {
      case _: Document => None
      case e: Element => Some { (preceding(e), following(e)) match {
        case (Nil, Nil) => e.getTagName
        case (els, _)   => e.getTagName + "[" + (els.size + 1) + "]"
      }}
    }.takeWhile(_.isDefined).map(_.get).reverse.mkString("/")
  }
  val node = doc.getElementsByTagName("text:p").item(1)
  println(node)
  val element: Element = node.asInstanceOf[Element]
  println(pathElement(element))
}
