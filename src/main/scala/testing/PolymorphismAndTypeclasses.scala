package testing

/**
  * Created by bunyod on 8/9/17.
  */
object PolymorphismAndTypeclasses extends App {

  trait Animal {
    def makeSound: String
  }

  class Dog extends Animal {
    override def makeSound = "Woof"
  }

  class Cat extends Animal {
    override def makeSound = "Meow"
  }

  trait OffspringName[T] {
    def offspringName(t: T): String
  }

  object OffspringName {
    implicit object CatHasOffspringName extends OffspringName[Cat] {
      override def offspringName(cat: Cat) = "Kitty"
    }

    implicit object DogHasOffspringName extends OffspringName[Dog] {
      override def offspringName(dog: Dog) = "Puppy"
    }
  }
  def offspringName[T : OffspringName](t: T): String = ???
//  def offspringName[T : OffspringName](t: T): String = {
//    implicitly[OffspringName[T]].apply(t)
//  }

  import OffspringName._

  val cat = new Cat
  val dog = new Dog
  println(offspringName(cat))
  println(offspringName(dog))

  class Cow extends Animal {
    override def makeSound = "Moo!"
  }

  val cow = new Cow
//  println(offspringName(cow))
}
