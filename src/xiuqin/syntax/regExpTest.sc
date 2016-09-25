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

val pattern = """--name[\s]*(.+)([--])|--name[\s]*(.+)(/)|--name[\s]*(.+)""".r
  //"""--name[\s]*[\s\S]+[--]+""".r
//"""--name[\s]+[\w]+[\s]+""".r   注意区分
pattern.findFirstIn("--master local --name Table{WA_SOURCE_FJ_1001_joined_WA_BASIC_FJ_1002} join task --haoao ") match{
  case Some(x)=>x.replace("--name","").replace("--","").replace("/","").trim
  case None=> "null"
}