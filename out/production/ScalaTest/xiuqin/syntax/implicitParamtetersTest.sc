/**
  */

abstract class SemiGroup[A] {
  def add(x: A, y: A): A
}

abstract class Monoid[A] extends SemiGroup[A] {
  def unit: A
}

object ImplicitTest {

  implicit object StringMonoid extends Monoid[String] {
    def add(x: String, y: String): String = x.concat(y)

    def unit: String = ""
  }

  implicit object IntMonoid extends Monoid[Int] {
    def add(x: Int, y: Int): Int = x + y

    def unit: Int = 0
  }

  def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
    if (xs.isEmpty) m.unit
    else m.add(xs.head, sum(xs.tail))    //是个递归调用
}

ImplicitTest.sum(List(1, 2, 3))
ImplicitTest.sum(List("a", "b", "c"))
ImplicitTest.sum(List(2.1, 3.4, 5.6))

//
//
//
object AppleUtiles{
  implicit class get(s:String){
    def getApple=new Apple(s)
  }
}
case class Apple(name:String)

object PearUtils {
  implicit class get(s: String){
    def getPear = new Pear(s)
  }
}

case class Pear(name: String)

object GetFruit{
  val apple:Apple=AppleUtiles.get("apple1").getApple
  val pear:Pear=PearUtils.get("pear1").getPear
}

