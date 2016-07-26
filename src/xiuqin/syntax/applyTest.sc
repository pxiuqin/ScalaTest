//
//apply and unapply
//

case class Apple(price: Int, weight: Int)

val apple = Apple(10, 20)
val Apple(p, w) = apple //output p and w
//split
def separate(s: String) = {
  val parts = s.split(",")
  if (parts.length == 3) (parts(0), parts(1), parts(2)) else None
}

//output number,name,addr
val (number, name, addr) = separate("11222,xiuqin,beijing")

//if no match
val (none) = separate("xiqin,beijing")

//val Student(number,name,addr)="11222,xiuqin,beijing"
object Student {
  //Extraction method
  def unapply(str: String): Option[(String, String, String)] = {
    val parts = str.split(",")
    if (parts.length == 3) Some(parts(0), parts(1), parts(2)) else None
  }

  //Injection method
  def apply(number: String, name: String, addr: String) = {
    number + "," + name + "," + addr
  }
}

//unapply
//compiler action val Some((number1,name1,addr1))=Student.unapply("1112222,xiuqin,beijing")
val Student(number1, name1, addr1) = "1112222,xiuqin,beijing"

//example
val student = List(
  "111,xiuqin1,beijing",
  "222,xiuqin2,beijing",
  "333,xiuqin3,beijing"
)

student.foreach(_ match {
  case Student(nb, name, addr) => println(nb + "," + name + "," + addr)
})

student.foreach(_ match {
  case Student(_, name, addr) if name == "xiuqin1" => println(name)
})
//create student xiuqin
val number2 = "321"
val name2 = "xiuqin2"
val addr2 = "beijing"

//apply
val data = Student(number2, name2, addr2)
println(data)


//other example
object Twice {
  def apply(x: Int): Int = x * 2

  def unapply(z: Int): Option[Int] = if (z % 2 == 0) Some(z / 2) else None
}

object TwiceTest extends App {
  val x = Twice(21)
  x match {
    case Twice(n) => Console.println(n)
  } //prints 21
}

/**
  * 定义和使用对象的apply方法，如下方式就会被调用
  */
//Object(pram1,....,paramx)   //通常一个apply方法返回的是伴生类的对象

Array("xiuqin","liang","hao")

//为什么不用构造器呢？对于嵌套表达式而言，省去new会方便
Array(Array(1,3),Array(2,4))   //二维数组

/**
  * 这里注意：Array(100)和new Array(100)很容易搞混。前者为调用apply(1000,输出一个单元素的
  * Array[Int];而第二个表达式调用构造器this(100)，结果是Array[Noting]，包含了100个null元素。
  */







