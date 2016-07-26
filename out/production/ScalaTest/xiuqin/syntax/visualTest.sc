//
//视界，就像类型边界，要求对给定的类型存在这样一个函数。您可以使用<%指定类型限制，例如：
//
class Container[A <% Int] {
  def addIt(x: A) = 123 + x
}

//fail
//(new Container[String]).addIt("123")

(new Container[Int]).addIt(123)

//fail
//(new Container[Float]).addIt(123.4F)

//
//A =:= B	A 必须和 B相等
//A <:< B	A 必须是 B的子类
//A <%< B	A 必须可以被看做是 B
//
class Container1[A](value: A) {
  def addIt(implicit evidence: A =:= Int) = 123 + value
}

(new Container1(123)).addIt

//fail
(new Container1("123")).addIt

class Container2[A](value: A) {
  def addIt(implicit evidence: A <%< Int) = 123 + value
}

(new Container2("123")).addIt

//
//使用视图进行泛型编程
//
def min[B >: A](implicit cmp: Ordering[B]): A = {
  if (isEmpty)
    throw new UnsupportedOperationException("empty.min")

  reduceLeft((x, y) => if (cmp.lteq(x, y)) x else y)
}

List(1, 2, 3, 4).min

List(1, 2, 3, 4).min(new Ordering[Int] {
  def compare(a: Int, b: Int) = b compare a
})

//
//作为旁注，标准库中有视图来将 Ordered 转换为 Ordering （反之亦然）。
//

trait LowPriorityOrderingImplicits {
  implicit def ordered[A <: Ordered[A]]: Ordering[A] = new Ordering[A] {
    def compare(x: A, y: A) = x.compare(y)
  }
}


//
//上下文边界和implicitly[]
//Scala2.8引入了一种串联和访问隐式参数的快捷方式。
//
def foo[A](implicit x: Ordered[A]) {}
def foo[A: Ordered] {}

implicitly[Ordering[Int]]


//
//F-界多态性
//通常有必要来访问一个（泛型）特质的具体子类。
//例如，想象你有一些泛型特质，但需要可以与它的某一子类进行比较。
//
trait Container4 extends Ordered[Container]

def compare(that: Container4): Int

//编译失败，因为我们对 Container 指定了Ordered特质，而不是对特定子类型指定的。
class MyContainer extends Container4 {
  def compare(that: MyContainer): Int
}

trait Contariner5[A <: Contariner5[A]] extends Ordered[A]

class MyContainer4 extends Contariner5[MyContainer4] {
  def compare(that: MyContainer4) = 0
}

List(new MyContainer4, new MyContainer4,new MyContainer4)
List(new MyContainer4, new MyContainer4,new MyContainer4).min