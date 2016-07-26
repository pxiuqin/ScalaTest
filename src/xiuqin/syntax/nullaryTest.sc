import com.sun.xml.internal.ws.client.sei.ResponseBuilder.Body

/**
  */

object TargetTest1 {
  def whileLoop(cond: => Boolean)(body: => Unit): Unit =
    if (cond) {
      body
      whileLoop(cond)(body)
    }
}

var j = 10
TargetTest1.whileLoop(j > 0) {
  println(j)
  j -= 1
}

object TargetTest2 {
  def loop(body: => Unit): LoopUnlessCond = new LoopUnlessCond(body)

  protected class LoopUnlessCond(body: => Unit) {
    def unless(cond: => Boolean) {
      body
      if (!cond) unless(cond)
    }
  }

}

var i = 10
TargetTest2.loop {
  println("i=" + i)
  i -= 1
}.unless(i == 0)

