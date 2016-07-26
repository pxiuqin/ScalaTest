//
//基本概念
//
object ImplicitDemo {

  def display(input: String): Unit = println(input)

  implicit def typeConvertor(input: Int): String = input.toString

  implicit def typeConvertor(input: Boolean): String = if (input) "true" else "false"

  //runtime conflict
  //implicit def booleanTypeConvertor(input:Boolean):String = if(input) "true" else "false"

  def main(args: Array[String]): Unit = {
    display("1212")
    display(12)
    display(true)
  }
}

//same domain
implicit def typeConvertor(input: Int): String = input.toString
ImplicitDemo.display(2)

//
//简单应用
//

/*这里 1->"game1"其实是1.->("game_1")的简写。
这里怎么能让整数类型1能有->方法呢。
这里其实any2ArrowAssoc隐式函数起作用了，这里接受的参数[A]是泛型的，所以int也不例外。
调用的是：将整型的1 implicit转换为 ArrowAssoc(1)
看下构造方法，将1当作__leftOfArrow传入。
->方法的真正实现是生产一个Tuple2类型的对象(__leftOfArrow,y ) 等价于(1, "game_id")
这就是一个典型的隐式转换应用。
*/
val mp = Map(1 -> "game1", 2 -> "game_2")

/*
其它还有很多类似的隐式转换，都在Predef.scala中：
例如：Int，Long，Double都是AnyVal的子类，这三个类型之间没有继承的关系，不能直接相互转换。
在Java里，我们声明Long的时候要在末尾加上一个L，来声明它是long。
但在scala里，我们不需要考虑那么多，只需要：
这就是implicit函数做到的，这也是scala类型推断的一部分，灵活，简洁。
其实这里调用是：
val l : Long = int2long(10)
 */
val l: Long = 10


/*
为现有的类库增加功能的一种方式，用java的话，只能用工具类或者继承的方式来实现，
而在scala则还可以采用隐式转化的方式来实现。
 */

//隐式参数
object ImplictDemo2 {

  object Context {
    implicit val ccc: String = "implicit"
  }

  object Param {
    def print(content: String)(implicit prefix: String) {
      println(prefix + ":" + content)
    }
  }

  def main(args: Array[String]) {
    Param.print("jack")("hello")

    import Context._
    Param.print("jack")
  }
}

//隐式转换扩展

/*
1.记住隐式转换函数的同一个scop中不能存在参数和返回值完全相同的2个implicit函数。
2.隐式转换函数只在意 输入类型，返回类型。
3.隐式转换是scala的语法灵活和简洁的重要组成部分
 */

import java.io.File

import scala.io.Source

class RichFile(val file: File) {
  def read = Source.fromFile(file.getPath()).mkString
}

object Context {
  implicit def file2RichFile(f: File) = new RichFile(f)
}

object ImplictDemo3 {
  def main(args: Array[String]) {
    import Context.file2RichFile
    println(new File("f:\\create.sql").read)
  }
}


//
//隐函数允许类型自动转换
//
implicit def strToInt(x: String) = x.toInt

val y:Int="132"

math.max("123",111)


#!/bin/bash
scala $0 $@
exit
!#

/*
 A demonstration of implicits for embedding
 domain-specific languages in Scala.  In this
 case, the DSL creates an AST for regular expressions.

 Exercise: Implement a matchesString method for RegEx
 */


abstract class RegEx {
  def ~ (right : RegEx) = Sequence(this,right)
  def || (right : RegEx) = Alternation(this,right)
  def * = Repetition(this)

  def matchesString(s : String) : Boolean =
    throw new Exception("An exercise for the reader!")
}

// Charaters:
case class CharEx(val c : Char)
  extends RegEx

// Sequences:
case class Sequence (val left : RegEx, val right : RegEx)
  extends RegEx

// Alternation:
case class Alternation (val left : RegEx, val right : RegEx)
  extends RegEx

// Kleene repetition:
case class Repetition (val exp : RegEx)
  extends RegEx

// Empty:
case object Empty extends RegEx


// Building regex's manually is cumbersome:
val rx1 = Sequence(CharEx('f'),Sequence(CharEx('o'),CharEx('o')))


// Automatically convert strings into regexes:
implicit def stringToRegEx(s : String) : RegEx = {
  var ex : RegEx = Empty
  for (c <- s) {
    ex = Sequence(ex,CharEx(c))
  }
  ex
}

// Implicits + operator overloading makes the syntax terse:
val rx2 = "baz" ~ ("foo" || "bar") * ;
println(rx2)
// Prints:
// Repetition(Sequence(Sequence(Sequence(Sequence(Empty,CharEx(b)),CharEx(a)),CharEx(z)),Alternation(Sequence(Sequence(Sequence(Empty,CharEx(f)),CharEx(o)),CharEx(o)),Sequence(Sequence(Sequence(Empty,CharEx(b)),CharEx(a)),CharEx(r)))))






