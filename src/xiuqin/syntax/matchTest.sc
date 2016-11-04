/*

 */

object MatchTest1 {
  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }
}

MatchTest1.matchTest(10)

object MatchTest2 {
  def matchTest(x: Any): Any = x match {
    case 1 => "one"
    case "two" => 2
    case y: Int => "scala.Int"
  }
}

MatchTest2.matchTest("two")

//添加守卫，守卫可以是任何Boolean条件
val ch = "xiuqin+liang"
ch match {
  case '+' => println(1)
  case '-' => println(0)
  case _ if Character.isDigit(1) => println("hao") //可以添加守卫
  case _ => println(-1)
}

//Scala是如何知道Pi是常量，而不是变量，背后的规则是，变量必须
//以小写字母开头，如果有小写字母需要用``使用
import scala.math._

10 match {
  case Pi => println("is pi")
}

import java.io.File._

"hhh" match {
  case `pathSeparator` => println("this is constant")
}

//
//类型模式,使用模式匹配，而不是isInstanceOf操作符
//注意必须给出一个变量名，否则会那对象本身来进行匹配
//
val intType: Any = 10
intType match {
  case x: Int => println("int type")
  case x: String => println("string type ")
  case _ => println("no type")
}

//匹配发生在运行期，Java虚拟机中泛型的类型信息是被擦除u的，
//因此不能用类型来匹配特定的Map
//但是对于数组而言元素的类型信息是完好的。你可以匹配到Array[Int]
//case m:Map[String, Int] => ... //别这样做
//case m: Map[_, _] => .....  //ok


//
//匹配数组、列表和元组
//
val arr: Any = Array(1, 2, 3)
arr match {
  case Array(0) => "0" //匹配包含0的数组
  case Array(x, y) => x + " + " + y //匹配带有两个元素的数组
  case Array(0, _*) => "0 ...." //匹配任何以0开始的数组
  case _ => "something else"
}

//同上述功能，应用于List
val list: Any = List(1, 2, 3)
list match {
  case 0 :: Nil => "0"
  case x :: y :: Nil => x + " " + y
  case 0 :: tail => "0 ...."
  case _ => "something else"
}

//元组表示法
val pair: Any = (0, 1)
pair match {
  case (0, _) => "0...."
  case (y, 0) => y + "0"
  case _ => "neither is 0"
}

//请注意变量是如何帮到列表或元组的不同部分的，由于这种绑定让
//你可以很轻松的访问复杂结构的各个组成部分，因此这样的操作成为“析构”

//正则表达式另一个适合使用提取器的场景，如果有分组，可以是用提取器
//来匹配每个分组
val pattern= "([0-9]+) ([a-z]+)".r
"99 bottlers" match {
  case pattern(num, item) => println("num and item")
}

//实质是执行了pattern.unapplySeq("99 bottlers")
//产生一些列匹配分组的字符串，这些字符串被分别赋值给了num和item
//注意这里的提取器并非是一个伴生对象，而是一个正则表达式对象