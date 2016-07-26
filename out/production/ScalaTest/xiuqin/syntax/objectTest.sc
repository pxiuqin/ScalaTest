/*
A singleton object can extend classes and traits.
In fact, a case class with no type parameters will by default create a singleton object of the same name, with a Function* trait implemented.
 */

//Object的伴随类
class IntPair(val x: Int, val y: Int)

object IntPair {

  import math.Ordering

  implicit def ipord: Ordering[IntPair] =
    Ordering.by(ip => (ip.x, ip.y))
}

/*
Frequently, Java programmers define static members, perhaps private, as implementation aids for their instance members. These move to the companion, too;
a common pattern is to import the companion object’s members in the class, like so:
 */

class X{
  import X._

  def blah=foo
}

object X{
  private def foo=32
}
