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
pattern.findFirstIn("--master local --name h::.32zzv o affa ") match{
  case Some(x)=>x.replace("--name","").replace("--","").replace("/","").trim
  case None=> "null"
}