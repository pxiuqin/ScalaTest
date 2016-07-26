
/*
函数参数的不同写法
 */
val f1 = ((a: Int, b: Int) => a + b)
val f2 = (a: Int, b: Int) => a + b
val f3 = (_: Int) + (_: Int)
val f4: (Int, Int) => Int = (_ + _)

//
//函数参数（这里使用{或(都可以，因为这里可以是匿名函数）
//
def m1(x: (Int) => String)(y: () => Int): String = {
  y().toString + x(100)
}

//函数调用1
m1 {
  (x: Int) => x.toString
} {
  () => 100
}

//函数调用2
m1(x => "xiu")(() => 100)

//函数定义2
def m2(x: Int)(y: Int => String) = {
  x
}

m2 {
  2
} { (x: Int) => "liangxiuqin" }