/**
  */

object forTest {
  def whileTest: Unit = {
    var n = 10
    while (n > 0) {
      println("hao")
      n -= 1
    }
  }

  def forTest1 = {
    var n = 10
    for (i <- 1 to n)
      print(i)
  }

  def utilTest: Unit = {
    val s = "hello"
    var sum = 0
    for (i <- 0 until s.length) //i的最后一个取值为length-1
      sum + s(i)

    //同上
    for (ch <- "hello")
      sum += 0
  }

  def forTest2: Unit = {
    //输出11 12 13 21 22 23 31 32 33
    for (i <- 1 to 3; j <- 1 to 3) print((10 * i + j) + "")

    //输出12 13 21 23 31 32
    for (i <- 1 to 3; j <- 1 to 3 if i != j) print((10 * i + j) + "")

    //13 22 23 31 32 33
    for (i <- 1 to 3; from = 4 - i; j <- from to 3) print((10 * i + j) + "")

    for {
      i <- 1 to 3
      from = 4 - i
      j <- from to 3
    } print((10 * i + j) + "")
  }

  //以下为for推导，生成的集合与它的第一生成器是类型兼容的
  def forTest3: Unit = {
    //生成Vector(1, 2, 0, 1, 2, 0, 1, 2, 0, 1)
    for (i <- 1 to 10) yield i % 3

    //生成 “HIeflmlmop”
    for (c <- "Hello"; i <- 0 to 1) yield (c + i).toChar

    //生成Vector('H', 'e', 'l', 'l', 'o', 'I', 'f', 'm', 'm', 'p')
    for (i <- 0 to 1; c <- "Hello") yield (c + i).toChar
  }

  //可变长
  def sum(args: Int*) = {
    var result = 0
    for (arg <- args) result += arg
    result
  }

  //递归，可以把tail转换成参数序列
  def recursiveSum(args: Int*): Int = {
    if (args.length == 0) 0
    else args.head + recursiveSum(args.tail: _*)
  }
}

val s=forTest.sum(1,23,3,4,5,5)
//val s2=forTest.sum(1 to 5)   //错误
val s2=forTest.sum(1 to 5 : _*)


