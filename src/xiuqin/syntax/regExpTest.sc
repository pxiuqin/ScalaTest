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

val wsnuwsPattern="""\s+[0-9]+\s""".r
for(matchString<-wsnuwsPattern.findAllIn("fjjfajf9993939"))
  println(matchString)

//上述也可以吧迭代器转换成数组
val matches=wsnuwsPattern.findAllIn("fajfj3333").toArray

//首个匹配想
wsnuwsPattern.findFirstIn("jjjjj")

//检查是否某个字符串的开始部分能匹配
wsnuwsPattern.findPrefixOf("jjajajf")   //None

//正则表达式组
val numitemPattern="([0-9]+) ([a-z]+)".r

//可以把正则表达式对系那个当成是提取器
val numitemPattern(num,item)="99 xiuqin"

//针对多个提取是用for
for(numitePattern(num,item)<-numitemPattern.findAllIn("99 xiuqin,100 liang"))