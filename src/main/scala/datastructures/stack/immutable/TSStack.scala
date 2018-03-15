package datastructures.stack.immutable

class TSStack[T](elements: List[T]) {

  def push(item: T): TSStack[T] = {
    new TSStack[T](item :: elements )
  }

  def pop(): (T, TSStack[T]) = {
    (elements.head, new TSStack(elements.tail))
  }

  def peek(): T = {
    if (!isEmpty) elements.head
    else throw new NoSuchElementException("top of empty stack")
  }

  def isEmpty: Boolean = elements.isEmpty

}
