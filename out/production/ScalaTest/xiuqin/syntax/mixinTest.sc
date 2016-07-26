/*

 */

abstract class AbsIteractor{
  type T
  def hasNext:Boolean
  def next:T
}

trait RichIterator extends AbsIteractor{
  def foreach(f:T=>Unit): Unit = {
    while(hasNext)
      f(next)
  }
}

class StringIterator(s:String) extends AbsIteractor{
  type T=Char
  private var i=0
  def hasNext=i<s.length()
  def next={
    val ch=s.charAt(i)
    i+=1
    ch
  }
}

class Iter extends StringIterator("xiuqin") with RichIterator
val iter=new Iter
iter.foreach(println)   // iter foreach println





