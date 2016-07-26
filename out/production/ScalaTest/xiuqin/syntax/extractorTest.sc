/*
 */

object Twice{
  def apply(x:Int):Int=x*2
  def unapply(z:Int):Option[Int]=if (z%2==0) Some(z/2) else None
}

object TwiceTest{
  val x=Twice(21)

  val p= x match{
    case Twice(n)=>n.toString()
  }
}

TwiceTest.x     //apply
TwiceTest.p     //unapply