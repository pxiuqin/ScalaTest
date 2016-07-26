/*
匿名函数学习
 */

val f1 = (x: Int) => x + 1

val f2 = new Function1[Int, Int] {
  def apply(x: Int): Int = x + 1
}

val f3 = (x: Int, y: Int) => "(" + x + "," + y + ")"
f3(1,2)

val f4=()=>{System.getProperty("user.dir")}
f4()

//There is also a very lightweight way to write function types. Here are the types of the three functions defined above:
/*Int=>Int
(Int,Int)=>String
()=>String*/

//This syntax is a shorthand for the following types:
/*Function1[Int,Int]
Function2[Int,Int,String]
Function0[String]*/
