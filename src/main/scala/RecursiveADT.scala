
object RecursiveADT extends App {

  //  val e = Plus(Num(1), Plus(Num(2), Mul(Num(3), Num(4)))
  //~oo
  //  val result: Int = e.eval()
  //~functional
  //  val result: Int = eval(e)

  sealed trait Expression {
    def eval(): Int
  }

  case class Num(value: Int)  extends Expression {
    def eval(): Int = value
  }

  case class Plus(lhs: Expression, rhs: Expression) extends Expression {
    def eval(): Int = {
      val leftExp: Int = lhs match {
        case n: Num => n.value
        case Plus(l, r) => l.eval + r.eval
        case Minus(l, r) => l.eval - r.eval
      }

      val rightExp: Int = rhs match {
        case Num(v) => v
        case Plus(l, r) => l.eval + r.eval
        case Minus(l, r) => l.eval - r.eval
      }
      leftExp + rightExp
    }

  }

  case class Minus(lhs: Expression, rhs: Expression) extends Expression {
    def eval(): Int = {
      val leftExp: Int = lhs match {
        case n: Num => n.value
        case Plus(l, r) => l.eval + r.eval
        case Minus(l, r) => l.eval - r.eval
      }

      val rightExp: Int = rhs match {
        case Num(v) => v
        case Plus(l, r) => l.eval + r.eval
        case Minus(l, r) => l.eval - r.eval
      }

      leftExp - rightExp
    }

  }

  object Expression {
    def eval(e: Expression) = e.eval()
  }

  val e = Plus(Num(6), Plus(Num(-11), Plus(Num(4), Minus(Num(3), Num(4)))))

  import Expression._
  println(e.eval())
  println(eval(e))

}
