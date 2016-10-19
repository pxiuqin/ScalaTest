/*
在数学和计算机科学中，高阶函数是至少满足下列一个条件的函数:

- 接受一个或多个函数作为输入
- 输出一个函数

数学中它们也叫做算子(运算符)或泛函。微积分中的导数就是常见的例子，
因为它映射一个函数到另一个函数。
 */


//假设有一个函数对给定两个数区间中的所有整数求和：
def sumRange(start: Int, end: Int): Int = {
  if (start > end) 0 else start + sumRange(start + 1, end)
}

//如果现在要求连续整数的平方和：
def square(x: Int): Int = x * x
def sumSquares(start: Int, end: Int): Int = {
  if (start > end) 0 else square(start) + sumSquares(start + 1, end)
}

//如果要计算2的幂次的和：
def powerOfTwo(x: Int): Int = if (x == 0) 1 else 2 * powerOfTwo(x - 1)
def sumPowersOfTwo(a: Int, b: Int): Int =
  if (a > b) 0 else powerOfTwo(a) + sumPowersOfTwo(a + 1, b)

//上面的函数都是从a到b的f(n)的累加形式，我们可以抽取这些函数中共同的部分重新编写函数sum，
// 其中定义的f作为一个参数传入到高阶函数sum中：
def sum(f: Int => Int, a: Int, b: Int): Int =
  if (a > b) 0 else f(a) + sum(f, a + 1, b)

def id(x: Int): Int = x
def squareNew(x: Int): Int = x * x
def powerOfTwoNew(x: Int): Int = if (x == 0) 1 else 2 * powerOfTwoNew(x - 1)

def sumInts(a: Int, b: Int): Int = sum(id, a, b)
def sumSquared(a: Int, b: Int): Int = sum(square, a, b)
def sumPowersOfTwoNew(a: Int, b: Int): Int = sum(squareNew, a, b)

//集合中有很多有用的高阶函数
//打印三角形
(1 to 9).map("*" * _).foreach(println)

//找出偶数
(1 to 9).filter(_ % 2 == 0).foreach(println)

//连乘法(二元参数)
(1 to 9).reduceLeft(_ * _) //形如((1*2)*3).....)

//二元参数做排序
"this is xiuqin Liang scalaTest".split(" ").sortWith(_.length < _.length)

//等同于上面的 _.length>_length
"this is xiuqin Liang scalaTest".split(" ").sortBy(_.length)(Ordering[Int].reverse)

//
//返回函数的高阶函数
//
def mulBy(factor: Double) = (x: Double) => factor * x
//比如mulBy(3) 返回函数(x:Double)=>3*x

//scala支持如下简写来定义上述柯理化函数
def mulBy(factor: Double)(x: Double) = factor * x

//定义一个多参数
def mulBy1(factor: Double, x: Double) = factor * x
//柯里化这个函数
val hao= mulBy1 _ .curried
