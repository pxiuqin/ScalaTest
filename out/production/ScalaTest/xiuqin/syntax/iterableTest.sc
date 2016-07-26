/*
 * Here is the actual implementation of iterator
 */
//def foreach[U](f:Elem=>U):Unit={
//  val it= iterator
//  while(it.hasNext)
//    f(it.next())
//}

/*
Iterable有两个方法返回迭代器：grouped和sliding。然而，这些迭代器返回的不是单个元素，
而是原容器（collection）元素的全部子序列。
这些最大的子序列作为参数传给这些方法。grouped方法返回元素的增量分块，
sliding方法生成一个滑动元素的窗口。
两者之间的差异通过REPL的作用能够清楚看出。
 */
val xs = List(1, 2, 3, 4, 5)

val git = xs.grouped(3)
git.next() //List(1,2,3)
git.next() //List(4,5)
//git.next()   //报错

val sit = xs.sliding(3)
sit.next() //List(1,2,3)
sit.next() //List(2,3,4)
sit.next() //List(3,4,5)
//sit.next()    //报错


val it = xs.iterator
it.next()

xs.takeRight(3) //List(3,4,5)
xs.dropRight(3) //List(1,2)

val ys = List(6, 7, 8)
xs.zip(ys)    //List[Int,Int]

/*
一对容器 xs 和ys的相应的元素合并到一个iterable ，实现方式是通过附加的元素x或y，
把短的序列被延展到相对更长的一个上。
 */
xs.zipAll(ys,2,3)
ys.zipAll(xs,2,3)     //区别二者

/*
把一对容器xs和它的序列，所包含的元素组成一个iterable
 */
xs.zipWithIndex   //List[(Int, Int)] = List((1,0), (2,1), (3,2), (4,3), (5,4))

//测试 xs 和 ys 是否以相同的顺序包含相同的元素。
xs.sameElements(ys)


/*
在Iterable下的继承层次结构你会发现有三个traits：Seq，Set，和 Map。
这三个Traits有一个共同的特征，它们都实现了PartialFunction trait以及它的应用和isDefinedAt 方法。
然而，每一个trait实现的PartialFunction 方法却各不相同。
 */

//使用位置索引，元素是从0开始编号
Seq(1,2,3)(1)
val seq=Seq(1,2,3)
seq(1)

//使用成员测试
Set('a','b','c')('b')
val set=Set('a','b','c')
set('b')

//使用的是选择
Map('a'->1,'b'->10,'c'->100)('b')
val map=Map('a'->1,'b'->10,'c'->100)
map('b')



