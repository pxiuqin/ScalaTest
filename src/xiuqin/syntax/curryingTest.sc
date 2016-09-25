/*
Methods may define multiple parameter lists.
When a method is called with a fewer number of parameter lists,
then this will yield a function taking the missing parameter lists as its arguments.
 */

object CurryTest {
  def filter(xs: List[Int], p: Int => Boolean): List[Int] =
    if (xs.isEmpty) xs
    else if (p(xs.head)) xs.head :: filter(xs.tail, p)
    else filter(xs.tail, p)

  def modN(n: Int)(x: Int) = ((x % n) == 0)
}

val nums = List(1, 2, 3, 4, 5, 6, 7, 8)
val l1 = CurryTest.filter(nums, CurryTest.modN(2))
val l2 = CurryTest.filter(nums, CurryTest.modN(3))

def multiply(m: Int)(n: Int): Int = m * n
multiply(2)(3)

val timesTwo = multiply(2) _

timesTwo(3)

//你可以对任何多参数函数执行柯里化
def adder(m: Int)(n: Int): Int = m + n
def adder1(m: Int, n: Int): Int = m + n

val add2 = (adder1 _).curried
adder1(2, 3)
add2(2)(3)

//val hao= add2(2) _   //不知为什么有错误

//可变参数
def capitalizeAll(args: String*) = {
  args.map { arg =>
    arg.capitalize
  }
}

capitalizeAll("liang", "xiuqin")

//高阶函数表示
def sum(f: Int => Int): (Int, Int) => Int = {
  def sumf(a: Int, b: Int) = {
    f(a) + f(b)
  }

  sumf
}

//相同功能的Curring化表示
def sum1(f: Int => Int)(a: Int, b: Int): Int = {
  f(a) + f(b)
}

sum((x:Int)=>x+1)(1,2)
val hao=sum((x:Int)=>x+1)
sum1((x:Int)=>x+1)(1,2)
val hao1=sum1((x:Int)=>x+1)_

