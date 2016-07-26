/**
  */

//构造函数默认参数
class HashMap[K, V](loadFactor: Float = 0.75f, initialCapactity: Int = 16) {

}

val m1 = new HashMap[String, Int]
val m2 = new HashMap[String, Int](20.0f)
val m3 = new HashMap[String, Int](20)
val m4 = new HashMap[String, Int](loadFactor = 0.8f)
val m5 = new HashMap[String, Int](initialCapactity = 10)
val m6 = new HashMap[String, Int](0.8f, 10)

//Named Parameters  具名参数
def printName(first: String = "xiuqin", last: String = "liang") = {
  println(first + " " + last)
}

printName("xiuqin", "liang")
printName(first = "xiuqin", last = "liang")
printName(last = "liang", first = "xiuqin")
printName("xiuqin")

//
//属性
//var foo:自动合成一个getter和一个setter
//val foo:自动合成一个getter
//由你来定义foo和foo_=方法
//有你来定义foo方法
//
class Person {
  private var privateAge = 0 //是有

  def age = privateAge

  def age_=(newValue: Int): Unit = {
    if (newValue > privateAge)
      privateAge = newValue
  }
}

class Counter {
  private var value = 0

  def increment() {
    value += 1
  }

  def current = value

  //private[this] var value = 0    如果这样定义容许访问value
  //private[Counter] var value = 0   指定某个类可以访问
  def isLess(other: Counter) = value < other.value
}

//JavaBeans规范
import scala.reflect.BeanProperty

class Person1 {
  @BeanProperty
  var name: String = _
}

//
//辅助构造器
//辅助构造器的名称为this;每一个辅助构造器都必须以一个对先前已定义的其他辅助构造器
//或主构造器的调用开始
class Person2 {
  private var name = ""
  private var age = 0

  //一个辅助构造器
  def this(name: String) {
    this() //调用主构造器
    this.name = name
  }

  //另一个辅助构造器
  def this(name: String, age: Int) {
    this(name) //调用前一个辅助构造器
    this.age = age
  }
}

//避免过多的使用辅助构造器
class Person3(val name: String = "", val age: Int = 0)

//私有构造器
class Person4 private(val id: Int)

//
//嵌套类
//可以在任何结构中内嵌任何语法结构，比如函数内嵌，类内嵌
import scala.collection.mutable.ArrayBuffer

class Network {

  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]
  }

  private val members = new ArrayBuffer[Member]

  def join(name: String) = {
    val m = new Member(name)
    members += m
    m
  }
}

//
//在Scala中，每个实例都有他自己的Member类，以下两个对象中的
//Member所属各自对象。这个Java不同，在Java中内部类从属于外部类

//
val chatter=new Network
val myFace=new Network
val me=new chatter.Member("hao")  //Java chatter.new Member()