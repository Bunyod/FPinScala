package problems

object PrimeNumbers extends App {

  def primes(n: Int): Seq[Int] = {
    (2 to n).foldLeft(Seq.empty[Int]){ (acc, curr) =>
      if (isPrime(curr)) acc :+ curr else acc
    }
  }

  def isPrime(k: Int): Boolean = {
    val endPointLoop = if (k < 10) {
      k / 2
    } else {
      math.sqrt(k)
    }
    def go(div: Int): Boolean = {
      if (div < endPointLoop + 1) {
        if (k % div == 0) false
        else go(div + 1)
      } else {
        true
      }
    }
    go(2)
  }

  println(primes(50))

  def primeStream(s: Stream[Int]): Stream[Int] =
    Stream.cons(s.head, primeStream(s.tail filter { _ % s.head != 0 }))
  val primeNums = primeStream(Stream.from(2))
  println(primeNums.take(20).toList)

  //2nd approach
  val primes2: Stream[Int] = 2 #:: Stream.iterate[Int](3)(x =>
    (x+2 to x*2 by 2).find(i => primes2.takeWhile(p => p*p <= i)
      .forall(i%_ > 0)).get)

  primes2.take(100).foreach(println)  // 2 3 5 etc.
}
