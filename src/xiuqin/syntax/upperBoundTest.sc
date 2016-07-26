/**
  * 确定上界
  * 可以看到方法的返回值是协变的位置
  */

trait Similar {
  def isSimilar(x: Any): Boolean
}

case class MyInt(x: Int) extends Similar {
  def isSimilar(m: Any): Boolean =
    m.isInstanceOf[MyInt] && m.asInstanceOf[MyInt].x == x
}

object UpperBoundTest {
  def findSimilar[T <: Similar](e: T, xs: List[T]): Boolean =
    if (xs.isEmpty) false
    else if (e.isSimilar(xs.head)) true
    else findSimilar[T](e, xs.tail)
}


val list: List[MyInt] = List(MyInt(1), MyInt(2), MyInt(3))
UpperBoundTest.findSimilar[MyInt](MyInt(4), list)
UpperBoundTest.findSimilar[MyInt](MyInt(2), list)


//逆变类的类型参数可以用在方法的参数类型上，
// 用做方法的返回值类型时必须使用上界绑定 <:
case class ListNode[-T](h: T, t: ListNode[T]) {
  def head: T = h

  def tail: ListNode[T] = t

  def prepend(elem: T): ListNode[T] = ListNode(elem, this)
}

case class ListNode1[-T](h: T, t: ListNode1[T]) {
  def head[U <: T]: U = {
    new U
  }
}