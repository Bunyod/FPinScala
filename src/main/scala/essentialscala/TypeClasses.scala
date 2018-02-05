package essentialscala

object TypeClasses {

  type ProductId = String
  type Quantity = Int

  case class ShoppingCart(items: Map[ProductId, Quantity])

  def merge(list: List[ShoppingCart]): ShoppingCart = {
    val emptyCart = ShoppingCart(Map())
    list.fold(emptyCart)(combineTwoShoppingCarts)
  }

  def combineTwoShoppingCarts(sc1: ShoppingCart, sc2: ShoppingCart): ShoppingCart = {
    ShoppingCart(combineItems(sc1.items, sc2.items))
  }

  def combineItems(m1: Map[ProductId, Quantity], m2: Map[ProductId, Quantity]): Map[ProductId, Quantity] = {
    (m1.keys ++ m2.keys)
      .toList
      .distinct
      .map(id => (id, m1.getOrElse(id, 0) + m2.getOrElse(id, 0)))
      .toMap
  }

  val carts = List(
    ShoppingCart(Map("p0001" -> 1, "p0002" -> 3)),
    ShoppingCart(Map("p0001" -> 4, "p0004" -> 6)))

  merge(carts)

  trait Combinable[A] {
    def empty: A
    def combine(a: A, b: A): A
    def combineAll(list: List[A]) = list.fold(empty)(combine)

  }

  object Combinable {
    def apply[A](implicit comb: Combinable[A]): Combinable[A] = comb

    def instance[A](emptyValue: A, combineFunc: (A, A) => A): Combinable[A] = {
      new Combinable[A] {
        def combine(a: A, b: A): A = combineFunc(a,b)
        def empty: A = emptyValue
      }
    }
  }

  implicit val intCombinableInstance: Combinable[Int] = Combinable.instance(0, _ + _)

  implicit def mapCombinableInstance[A,B](implicit b: Combinable[B]): Combinable[Map[A, B]] = {
    def merge(map1: Map[A, B], map2: Map[A, B]): Map[A, B] = {
      (map1.keys ++ map2.keys)
        .toList
        .distinct
        .map(a => (a, b.combine(
          map1.getOrElse(a, b.empty),
          map2.getOrElse(a, b.empty))))
        .toMap
    }
    Combinable.instance(Map(), merge)
  }
  implicit val shoppingCartCombinableInstance: Combinable[ShoppingCart] = {
    Combinable.instance(
      ShoppingCart(
        Map()),
      (m1, m2) => ShoppingCart(Combinable[Map[ProductId, Quantity]].combine(m1.items, m2.items)))
  }

  Combinable[ShoppingCart].combineAll(carts)

//  If you want to practice, here are two exercises:
//
//    Take the code from this post and define Combinable instances for String, Boolean, and List (or any other type that seems appropriate).
//  Implement a type class Printable which prints a String representation of a value to the console.
}
