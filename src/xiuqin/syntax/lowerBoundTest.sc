/**
  * 确定下界
  * 可以看出方法的参数是逆变的位置。
  */

case class ListNode[+T](h: T, t: ListNode[T]) {
  def head: T = h

  def tail: ListNode[T] = t

  def prepend(elem: T): ListNode[T] = ListNode(elem, this)
}

//因此协变类的类型参数可以用在方法的返回值的类型，
// 在方法的参数类型上必须使用下界绑定 >:
case class ListNode1[+T](h: T, t: ListNode1[T]) {
  def head: T = h

  def tail: ListNode1[T] = t

  //为了在方法的参数中使用类型参数，你需要定义下界：
  def prepend[U >: T](elem: U): ListNode1[U] =
    ListNode1(elem, this)
}


object LowerBoundTest {
  val empty: ListNode1[Null] = ListNode1(null, null)
  val strList: ListNode1[String] = empty.prepend("hello").prepend("world")
  val anyList: ListNode1[Any] = strList.prepend(12345)
}

LowerBoundTest.strList
LowerBoundTest.anyList
