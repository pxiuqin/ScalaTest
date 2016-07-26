/**
  */

implicit def list2ordered[A](x: List[A])(implicit elem2ordered: A => Ordered[A]): Ordered[List[A]] = new Ordered[List[A]] {

}

implicit def int2ordered(x: Int): Ordered[Int] = new Ordered[Int] {

}

implicit def int2Integer(x: Int) = java.lang.Integer.valueOf(x)

//一般来说，scala编译器会首先在方法调用处的当前范围内查找隐式转换函数；
// 如果没有找到，会尝试在源类型或目标类型（包括源类型和目标类型的类型变量的类型）的伴随对象中查找转换函数，
// 如果还是没找到，则拒绝编译。
object ABCD {

  class B

  class C {
    override def toString() = "I am C"

    def printC(c: C) = println(c)
  }

  class D

  implicit def B2C(b: B) = {
    println("B2C")
    new C
  }

  implicit def D2C(d: D) = {
    println("D2C")
    new C
  }

  new D().printC(new B)
}

//先调用 D2C转换函数将new D()转换成C类, 然后调用C类的printC方法, 但发现传入的参数类型是A类。
//由于当前范围无合适的转换函数，故搜索object A和object C内有无合适的转换函数，最后发现object A内有合适的转换函数。
//如果同时在object A和object C内发现合适的转换函数，有可能导致编译错误。
object ABCD2 {

  class A

  object A {
    //  implicit def A2C(a: A) = {
    //    println("A.A2C");
    //    new C
    //  }
  }

  class C {
    override def toString() = "I am C";

    def printC(c: C) = println(c);
  }

  object C {
    implicit def A2C(a: A) = {
      println("C.A2C");
      new C
    }
  }

  class D

  implicit def D2C(d: D) = {
    println("D2C")
    new C
  }

  new D().printC(new A)
}

/**
  *
  * 1.命名函数的参数可以声明为implicit,但implicit必须出现在首位,并且是对所有的参数有效，不能只给某些参数声明为implicit,比如：
  * def maxFunc(implicit i1: Int, i2: Int) = i1 + i2
  * maxFunc带有两个implicit参数i1和i2。你无法只声明一个implicit参数。你不能这样写
  * def maxFunc( i1: Int, implicit i2: Int) = i1 + i2
  * 也不能这样写：
  * def maxFunc(implicit i1: Int, implicit i2: Int) = i1 + i2
  * 如果你只想声明一个implicit，使用curry，如
  * def maxFunc(implicit i1: Int)(i2: Int) = i1 + i2
  *
  * 2. 匿名函数不能声明隐式参数，即不能这样写：
  * val f = (implicit s: String) => s+1
  *
  * 3. 如果一个函数带有implicit参数，则无法通过 _ 得到该函数引用。你尝试这样做是无法编译的：
  * def maxFunc(implicit i1: Int, i2: Int) = i1 + i2
  * val f = maxFunc _     // 编译错误
  *
  * 4. 可以给匿名函数的参数加上implicit，比如:
  * def h( implicit s: String) = println("here : "+s)
  * def g(func: String => Int) = {
  * println(func("a"))
  * }
  * g{
  * implicit s =>  h; 2
  * }
  * 这里的implicit s => h; 2相当于 s => implicit val xx = s; h; 2.
  * 如果匿名函数有两个参数，貌似不能给参数加上implicit
  */
