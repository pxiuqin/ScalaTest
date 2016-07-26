/*
对给定的输入参数类型，函数可接受该类型的任何值。换句话说，一个(Int) => String 的函数可以接收任意Int值，并返回一个字符串。
对给定的输入参数类型，偏函数只能接受该类型的某些特定的值。一个定义为(Int) => String 的偏函数可能不能接受所有Int值为输入。
isDefinedAt 是PartialFunction的一个方法，用来确定PartialFunction是否能接受一个给定的参数。
注意 偏函数PartialFunction 和我们前面提到的部分应用函数是无关的。
 */

val one:PartialFunction[Int,String]={case 1 =>"one"}

one.isDefinedAt(1)
one.isDefinedAt(2)

//call this function
one(1)
//one(3)   //error


//PartialFunctions可以使用orElse组成新的函数，
// 得到的PartialFunction反映了是否对给定参数进行了定义。
val two:PartialFunction[Int,String]={case 2 => "two"}
val three:PartialFunction[Int,String]={case 3 => "three"}
val wildcard:PartialFunction[Int,String]={case _ => "something else"}

val partial=one.orElse(two).orElse(three).orElse(wildcard)

partial(10)
partial(2)
partial(3)