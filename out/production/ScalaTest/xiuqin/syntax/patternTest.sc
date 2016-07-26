#!/bin/bash
scala $0 $@
exit
!#

/*
 Pattern-matching frequently replaces conditionals in Scala.
 */

10 match {
  case 10 => println("It's 10.")
}
// Prints:
// It's 10.


"foo" match {
  case "foo" => println("It's foo.")
}
// Prints:
// It's foo.


val x : Any = 3
x match {
  case "foo" => println("It's foo.")
  case 3 => println("It's 3.")
}
// Prints:
// It's 3.


val y : Any = 10
y match {
  case _ : String => println("It's a string.")
  case _ : Int => println("It's an integer.")
}
// Prints:
// It's an integer.


// Case classes are matchable:
case class Pair(val x : Int, val y : String)

val p = Pair(42,"foo")

p match {
  case Pair(43,"foo") => println("Not me.")
  case Pair(42,s) => println("It's " + s + ".")
}
// Prints:
// It's foo.


// unapply allows the creation of pseudo-case-classes from Objects:
object StringCons {

  def apply(c : Char, s : String) = c + s

  def unapply(s : String) : Option[(Char,String)] = s match {
    case "" => None
    case _ => Some((s.charAt(0),s.substring(1)))
  }
}

"foo" match {
  case StringCons('x',rest) => println("Not me.")
  case StringCons('f',rest) => println("The rest is " + rest + ".")
}
// Prints:
// The rest is oo.


// Taking advantage of operator sugar enhances readability:
object :+: {
  def apply(c : Char, s : String) = c + s

  def unapply(s : String) : Option[(Char,String)] = s match {
    case "" => None
    case _ => Some((s.charAt(0),s.substring(1)))
  }
}

"foo" match {
  case 'f' :+: "ooo" => println("Not me.")
  case 'f' :+: 'o' :+: rest => println("The rest is " + rest + ".")
}


// unapplySeq allows for multi-arity extractors:
object Factor {

  def factor(n : Int) : List[Int] = n match {
    case 1 => List.empty
    case n => {
      for (i <- 2 to n)
        if ((n % i) == 0)
          return i :: factor(n / i)
      return List(n,1)
    }
  }

  def unapplySeq(n : Int) : Option[List[Int]] = {
    return Some(factor(n))
  }
}

// 120 = 2 * 2 * 2 * 3 * 5
120 match {
  case Factor(a,b,c) => println("Not me.")
  case Factor(a,b,c,d,e) => println((a,c,e))
}
// Prints:
// (2,2,5)



// Pattern-matchable lists can be created from scratch:
abstract class MyList[+A] {
  def :*: [B >: A] (head : B) = new `:*:`(head,this)
}

case class :*:[A](val head : A, val tail : MyList[A]) extends MyList[A]

case object MyNil extends MyList[Nothing]

val l : MyList[Int] = 3 :*: 4 :*: 5 :*: MyNil

l match {
  case hd :*: tl => println(hd)
}

// Prints
// 3