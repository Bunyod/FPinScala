package testing

/**
  * Created by bunyod on 7/18/17.
  */
object  CategoryTheory extends App {

  class MyBox[T](val value: T)

  val boxedString: MyBox[String] = new MyBox("Hello")

  //Functor
  def map[A, B](rawfunc: A => B): MyBox[A] => MyBox[B] = (a: MyBox[A]) => new MyBox(rawfunc(a.value))

  //Monad
  def apply[A, B](b: MyBox[A => B]): MyBox[A] => MyBox[B] = (a: MyBox[A]) => new MyBox(b.value(a.value))

  //Applicative
  def flatMap[A, B](func: A => MyBox[B]): MyBox[A] => MyBox[B] =
    (a: MyBox[A]) => func(a.value)

  def rawLengthOf(a: String): Int = a.length
  val transformedLengthOf = map(rawLengthOf)
  val result: MyBox[Int] =transformedLengthOf(boxedString)
  println(result.value)
  def lengthOf(a: String) = new MyBox(a.length)


  val transformedLengthOf1 = flatMap(lengthOf)
  val result2 = transformedLengthOf1(boxedString)
  println(result2.value)

  val boxedLengthOf: MyBox[String => Int] = new MyBox(rawLengthOf _)

  val transformedLengthOf2 = apply(boxedLengthOf)
  val result3: MyBox[Int] = transformedLengthOf2(boxedString)
  println(result3.value)
}
