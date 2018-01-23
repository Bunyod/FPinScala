package crackcodinginterview.chapter1

import java.util

import scala.collection.immutable.IntMap
import scala.collection.mutable


object DynamicArrayList extends App {

  val words = Array("asdf", "qwer", "desfg")
  val more = Array("asdf1", "qwer2", "desfg3")

  val mList = new mutable.ResizableArray[String] {
    size0 = 16
    override def update(idx: Int, elem: String) {
//      println(size0)
//      println(array.toList)
      if (idx >= size0) throw new IndexOutOfBoundsException(idx.toString)
      array(idx) = elem.asInstanceOf[AnyRef]
    }
  }

  for {
    i <- words.indices
  } yield {
    mList.update(i, words(i))
  }

  for {
    i <- more.indices
  } yield {
    mList.update(i + 3, more(i))
  }

  println(mList.toList)
}