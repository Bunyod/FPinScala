package euler

object Problems {

//  Problem 1
//  Add all the natural numbers below one thousand that are multiples of 3 or 5. *
  val sum = (1 to 1000).filter(n => n%3==0 || n%5==0).sum
  assert(sum == 233168) // 7 ms
//  sum: Int = 233168

//  Problem 2
//  Find the sum of all the even-valued terms in the Fibonacci sequence which do not exceed four million. *

  lazy val fs: Stream[Int] = 0 #:: fs.scanLeft(1)(_ + _)
  val res = fs.view.takeWhile(_ <= 4000000).filter(_ %2 ==0)
}
