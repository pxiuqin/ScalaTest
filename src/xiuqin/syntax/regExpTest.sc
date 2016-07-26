/*

 */

object RegExpTest {
  def containsScala(x: String): Boolean = {
    val z: Seq[Char] = x
    z match {
      case Seq('s', 'c', 'a', 'l', 'a', rest@_*) =>
        true
      case Seq(_*) =>
        false
    }
  }
}

RegExpTest.containsScala("scala")
RegExpTest.containsScala("scalahao")
RegExpTest.containsScala("java")