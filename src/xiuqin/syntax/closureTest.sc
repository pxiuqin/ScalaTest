//对于访问不在作用域内的变量，实现的发那个是十一哦那个了闭包


//分析：
/*
1、mulBy的首次调用将参数factor设置3.该变量在(x:Double)=>factor * x函数的函数体内被引用，该函数
被存入triple,然后参数变量factor从运行时的栈上被弹出。
2、接下来,mulBy再次被调用,这次factor被设置为了0.5.该变量在(x:Double)=>factor * x函数的函数体内被
引用,该函数被存入到half

这样一个函数被称为闭包,闭包有代码和代码用到的任何局部变量定义构成，这些函数实际上是以类的对象
方式实现的,该类有一个实例变量factor和一个包含了函数体的apply方法
 */

def mulBy(factor: Double) = (x: Double) => factor * x

val triple = mulBy(3)
val half = mulBy(0.5)
println(triple(14) + " " + half(14))  //打印42 7

