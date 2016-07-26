/**
  * 抽象数据类型
  */
trait Buffer {
  type T
  val element: T
}

abstract class SeqBuffer extends Buffer {
  type U
  type T <: Seq[U]

  def length = element.length
}

abstract class IntSeqBuffer extends SeqBuffer {
  type U = Int
}

object AbstractTypeTest1 {
  def newIntSeqBuf(elem1: Int, elem2: Int): IntSeqBuffer = new IntSeqBuffer {
    type T = List[U]
    val element = List(elem1, elem2)
  }

}

val buf = AbstractTypeTest1.newIntSeqBuf(7, 8)
buf.length
buf.element

/**
  * 上述等同于：类型参数
  */

abstract class Buffer2[+T] {
  val element: T
}

abstract class SeqBuffer2[U, +T <: Seq[U]] extends Buffer2[T] {
  def length = element.length
}

object AbstractTypeTest2 {
  def newIntSeqBuf(e1: Int, e2: Int): SeqBuffer2[Int, Seq[Int]] = new SeqBuffer2[Int, List[Int]] {
    val element = List(e1, e2)
  }

}

val buf2 = AbstractTypeTest2.newIntSeqBuf(7, 8)
buf2.length
buf2.element

