//正则表达式练习

import io.Source

object RegexTest{
  val source=Source.fromFile("test.txt").mkString

  def basicRegexTest: Unit ={
    val pattern="\\w+\\s+\"".r
    pattern.findAllIn(source).foreach(println)
  }

  //找出非浮点数的词法单元
  def noFloatRegexTest: Unit ={
    val pattern="""[^(\d+\.){0,1}\d+)^\s+]+""".r
    pattern.findAllIn(source).foreach(println)
  }

  //找出网页中img标签的src属性
  def imgRegexTest: Unit ={
    val pattern="""<img[^>]+(src\s*=\s*"[^>^"]+")[^>]*>"""
  }
}