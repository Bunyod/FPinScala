package problems.tricks

object TailRecursionVsRecursion {

  def factorial(n: Int): Int = {
    if(n == 0) 1 else n*factorial(n-1)
  }

  println(factorial(5))
}
