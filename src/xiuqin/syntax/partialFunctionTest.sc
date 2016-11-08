/*
对给定的输入参数类型，函数可接受该类型的任何值。换句话说，一个(Int) => String 的函数可以接收任意Int值，并返回一个字符串。
对给定的输入参数类型，偏函数只能接受该类型的某些特定的值。一个定义为(Int) => String 的偏函数可能不能接受所有Int值为输入。
isDefinedAt 是PartialFunction的一个方法，用来确定PartialFunction是否能接受一个给定的参数。
注意 偏函数PartialFunction 和我们前面提到的部分应用函数是无关的。
 */

val one: PartialFunction[Int, String] = {
  case 1 => "one"
}

one.isDefinedAt(1)
one.isDefinedAt(2)

//call this function
one(1)
//one(3)   //error


//PartialFunctions可以使用orElse组成新的函数，
// 得到的PartialFunction反映了是否对给定参数进行了定义。
val two: PartialFunction[Int, String] = {
  case 2 => "two"
}
val three: PartialFunction[Int, String] = {
  case 3 => "three"
}
val wildcard: PartialFunction[Int, String] = {
  case _ => "something else"
}

val partial = one.orElse(two).orElse(three).orElse(wildcard)

partial(10)
partial(2)
partial(3)

val f: PartialFunction[Char, Int] = {
  case '+' => 1;
  case '-' => -1
}

f('-') //调用f.apply('-')，返回-1
f.isDefinedAt('0') //false
f('0') //抛出一个MatchError异常

//有一些方法接受Partial Function作为参数，比如GenTraversabel特质的
//collect方法将一个偏函数应用到所有在该偏函数有有定义的元素，并返回包含
//这些结果的序列
"-3+4".collect {
  case '+' => 1;
  case '-' => -1
}  //结果：Vecotr(-1,1)


//
//注意偏函数表达式必须是可以推断出返回类型的上下文中。
//当你把他赋值给一个带有类型声明的变量，或者将他作为参数
//传递时，都符合这个要求。
//