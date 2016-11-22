import scala.runtime.RichInt

/** *
  *
  */

//泛型类
class Pair[T, S](val first: T, val second: S)

val p = new Pair(42, "String") //这是一个Pair[Int,String]
val p2 = new Pair[Any, Any](42, "String") //指定类型

//泛型函数,函数后的类型参数不能去掉
def getMiddle[T](a: Array[T]) = a(a.length / 2)
getMiddle(Array("liang", "xiu", "qin"))

def getMiddle[T, D](a: Array[T], b: Seq[D]) = a(a.length / 2)

//类型变量界定
class Pair2[T](val first: T, val second: T)

//这是错误的，我们并不知道first是否有compareTo方法
class Pair3[T](val first: T, val second: T) {
  //def smaller = if (first.comparTo(second) < 0) first else second //错误
}

//要解决这个问题，可以添加一个上界T<:Comparable[T]
class Pair4[T <: Comparable[T]](val first: T, val second: T) {
  def smaller = if (first.compareTo(second) < 0) first
  else second;
}

val p4 = new Pair4("liang", "xiuqin")

//报错，因为Int不是Comparable[Int]的子类
//val p5 = new Pair4(13, 21)

//
//使用视图界定解决这个问题，视图界定恰好是实现一个隐式的转换
//如上述，会把Int隐式转换成RichInt
class Pair5[T <% Comparable[T]](val first: T, val second: T) {
  def smaller = if (first.compareTo(second) > 0) first else second
}

val p6 = new Pair5(12, 54)

//使用Ordered特质会更好,它在Comparable的基础上额外提供了关系操作符
class Pair6[T <% Ordered[T]](val first: T, val second: T) {
  def smaller = if (first < second) first else second
}

//试想，上述在String时他只实现了Comparable[String]没有实习Ordered[T]
//有了视图界定，字符串可以隐式的转换成RichString，而RichString是Ordered[String]的子类型

//
//下界
//

//假设一个对偶替换
class Pair7[T](val first: T, val second: T) {
  def replaceFirst(newFirst: T) = new Pair7[T](newFirst, second)

  //假定我们有个Pair7[Student],我们应该允许一个Person类来替换第一个组件
  //这样将返回一个Pair7[Person],通常而言替换进来的类型必须是原来的超类型
  def replaceFirst2[R >: T](newFirst: R) = new Pair7[R](newFirst, second)

  //在上例中给返回的对偶也写上了类型参数。当然你可以写出下面这样
  def replaceFirst3[R >: T](newFirst: R) = new Pair7(newFirst, second) //返回类型被正确的推断为Parir7[R]

  //如果你不写下界,返回的是Pair[Any]
  def replaceFirst4[R](newFirst: R) = new Pair7(newFirst, second)
}

//
//上下文界定
//视图界定T<%V要求必须存在一个从T到V的隐式转换，上下文界定的形式
//为T:M,其中M是另一个泛型类，要求必须存在一个类型为M[T]的“隐式值”
//
class Pair8[T: Ordering](val first: T, val second: T) {
  def smaller(implicit ord: Ordering[T]) = {
    if (ord.compare(first, second) < 0) first else second
  }
}

//Manifest上下文
//编写一个泛型函数来构造泛型数组，需要传入Manifest对象

def makePair[T: Manifest](first: T, second: T): Unit = {
  val r = new Array[T](2)
  r(0) = first
  r(1) = second
  r
}

//
//多重界定
//
//能同时有上界和下界
// T >: Lower <:Upper

//不能同时有多个上界或下界，不过可以要求一个类型实现多个特质
// T <: Comparable[T] with Serializable with Cloneable

//多个视图界定
// T <% Comparable[T] <% String

//多个上下文界定
// T : Ordering : Manifest

//
//类型约束
//

