package scalaexercises.beginner

object Exercises extends App {

  // You are not permitted to use these List methods:
  // * length
  // * map
  // * filter
  // * ::: (and variations such as ++)
  // * flatten
  // * flatMap
  // * reverse (and variations i.e. reverseMap, reverse_:::)
  // This also means you are not permitted to use for-comprehensions on Lists.
  // You are permitted to use the functions you write yourself. For example, Exercise 2 may use Exercise 1 or Exercise 3.
  // Using permitted existing methods where appropriate will attract marks for elegance.

  def succ(n: Int) = n + 1
  def pred(n: Int) = n - 1
  // Exercise 1
  // Relative Difficulty: 1
  // Correctness: 2.0 marks
  // Performance: 0.5 mark
  // Elegance: 0.5 marks
  // Total: 3
  //error("todo: Assume x and y are 0 or positive. Do not use + or - on Int. O
  def add(x: Int, y: Int): Int = {
    if (y == 0) x
    else {

      val a = x ^ y
      val b = x & y
      val c = (x & y)<< 1
      println(s"X=$x")
      println(s"Y=$y")
      println(s"A=$a")
      println(s"B=$b")
      println(s"C=$c")
      println("------------")
      add(x ^ y, (x & y) << 1)
    }
  }

  //  println(add(120,9))

  // Exercise 2
  // Relative Difficulty: 2
  // Correctness: 2.5 marks
  // Performance: 1 mark
  // Elegance: 0.5 marks
  // Total: 4
  val list = List(2,6,4,56,34,12,68,1)
  def sum(x: List[Int]): Int = {
    def go(xs: List[Int], acc: Int): Int = xs match {
      case h :: t => go(t, add(acc, h))
      case Nil => acc
    }
    go(x, 0)
  }
  def sum1(x: List[Int]): Int = x.foldLeft(0)(add)
  println(sum1(list))
  println(sum(list))
  // Exercise 3
  // Relative Difficulty: 2
  // Correctness: 2.5 marks
  // Performance: 1 mark
  // Elegance: 0.5 marks
  // Total: 4
  def length1[A](x: List[A]): Int = x.foldLeft(0)((a, _) => a + 1)
  def length[A](x: List[A]): Int = {
    def go(xs: List[A], acc: Int): Int = xs match {
      case _ :: t => go(t, acc + 1)
      case Nil => acc
    }
    go(x, 0)
  }
  println(length1(list))
  println(length(list))
  // Exercise 4
  // Relative Difficulty: 5
  // Correctness: 4.5 marks
  // Performance: 1.0 mark
  // Elegance: 1.5 marks
  // Total: 7
  case class Id(a: Int)
  def intToId(a: Int): Id = Id(a)
  def map[A, B](x: List[A], f: A => B): List[B] = {
    if (x.isEmpty) Nil
    else f(x.head) :: map(x.tail, f)
  }
  println(map(list, intToId))
  // Exercise 5
  // Relative Difficulty: 5
  //  http://blog.tmorris.net/posts/scala-exercises-for-beginners/index.html
  //  1/312/1/2017
  //  λ Tony's blog λ - Scala exercises for beginners
  // Correctness: 4.5 marks
  // Performance: 1.5 marks
  // Elegance: 1 mark
  // Total: 7
  def filter[A](x: List[A], f: A => Boolean): List[A] = {
    if (x.isEmpty) Nil
    else {
      if (f(x.head)) x.head :: filter(x.tail, f)
      else filter(x.tail, f)
    }
  }

//  def isEven(x: Int) = x % 2 == 0
//  println(filter(list, isEven))
  println(filter(list, (x: Int) => x % 2 == 0))
  // Exercise 6
  // Relative Difficulty: 5
  // Correctness: 4.5 marks
  // Performance: 1.5 marks
  // Elegance: 1 mark
  // Total: 7
  def append[A](x: List[A], y: List[A]): List[A] = {
    if (x.isEmpty && y.isEmpty) Nil
    else {
      if (x.nonEmpty) x.head :: append(x.tail, y)
      else if(y.nonEmpty)
        y.head :: append(x, y.tail)
      else append(x, y)
    }
  }
  val ys = List(3,14,15)
  println(append(list, List(1,2,3,4,5,6,7,8,9,7,6,5)))
  // Exercise 7
  // Relative Difficulty: 5
  // Correctness: 4.5 marks
  // Performance: 1.5 marks
  // Elegance: 1 mark
  // Total: 7
  def concat[A](x: List[List[A]]): List[A] = {
    if (x.isEmpty) Nil
    else {
      append(x.head, concat(x.tail))
    }
  }

  println(concat(List(List(1,2),List(3,4), List(5,6), List(7,8,6), List(9,6,0))))
  // Exercise 8
  // Relative Difficulty: 7
  // Correctness: 5.0 marks
  // Performance: 1.5 marks
  // Elegance: 1.5 mark
  // Total: 8
  def concatMap[A, B](x: List[A], f: A => List[B]): List[B] = {
    if (x.isEmpty) Nil
    else concat(map(x, f))
  }
  // Exercise 9
  // Relative Difficulty: 8
  // Correctness: 3.5 marks
  // Performance: 3.0 marks
  // Elegance: 2.5 marks
  // Total: 9
  def maximum(x: List[Int]): Int = {
    def go(xs: List[Int], res: Int): Int = xs match {
      case h :: t => if (res < h) {
        go(t, h)
      } else {
        go(t, res)
      }
      case Nil => res
    }
    go(x, Int.MinValue)
  }
  println(maximum(list))
  // Exercise 10
  // Relative Difficulty: 10
  // Correctness: 5.0 marks
  // Performance: 2.5 marks
  // Elegance: 2.5 marks
  // Total: 10
  def reverse[A](x: List[A]): List[A] = {
    if(x.isEmpty) Nil
    else reverse(x.tail) :+ x.head
  }
  println(reverse(list))
}
