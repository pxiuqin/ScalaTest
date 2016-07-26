/*

 */

val map1 = Map(1 -> "one", 2 -> "two", 3 -> "three")
val map2 = Map(6 -> "six", 7 -> "saven", 8 -> "eight")


//
//Lookups
//
map1.get(1)
map1.get(4)

map1(1)
map1(4)
map1.getOrElse(4, 10) //default 10 if not find 4(key)
map1.contains(1)
map1.contains(4)
map1.isDefinedAt(1)
//map1.isDefinedAt(4)   //报错

//
//Additions and Updates
//
map1 + (10 -> "ten")
map1 +(10 -> "ten", 11 -> "eleven")

map1 ++ map2


map1.updated(21, "21")

//
//Removals
//
map1 - 1
map1 -(1, 2, 3)


//
//Subcollection
//
map1.keys //return key iterable
map1.keySet //key set
map1.keysIterator //return iterator

map1.values //return value iterable
map1.valuesIterator

//
//Transformation
//
map1.filterKeys((x: Int) => x > 2)

//用f将ms中每一个键值对的值转换成一个新的值，
// 进而返回一个包含所有新键值对的映射视图（view）。
map1.mapValues((x: String) => x + "end")


//
//Mutable.Map
//
val m1 = collection.mutable.Map(1 -> "one", 2 -> "two", 3 -> "three")
val m2 = collection.mutable.Map(1 -> "1", 2 -> "2", 3 -> "3")

//
//Add and update
//
m1(1) = "10"
m1(4) = "4"

m1 += (11 -> "11")
m1 +=(11 -> "11", 12 -> "12")

m1 ++ m2

//向映射ms增加一个以k为键、以v为值的映射，
// 并返回一个Option，其中可能包含此前与k相关联的值。
m1.put(13, "13")

m1.getOrElse(1, "10")

//如果ms中存在键k，则返回键k的值。否则向ms中新增映射关系k -> v并返回d。
m1.getOrElseUpdate(1, "10")

//
//Removals
//
m1 -= 1
m1 -=(1, 2, 3)
//m1-=m2

//从ms中移除以k为键的映射关系，
// 并返回一个Option，其可能包含之前与k相关联的值。
m1.remove(1)

//仅保留ms中键满足条件谓词p的映射关系。
m2.retain((x: Int, v: String) => x < 10)

m2.clear()

//
//Transformation
//
m1.transform((x: Int, v: String) => v + "aho")

m1.clone()

/*
映射（Map）的添加和删除操作与集合（Set）的相关操作相同。
同集合（Set）操作一样，可变映射（mutable maps）也支持非破坏性（non-destructive）修改操作+、-、和updated。
但是这些操作涉及到可变映射的复制，因此较少被使用。而利用两种变形m(key) = value和m += (key -> value)， 我们可以“原地”修改可变映射m。
此外，存还有一种变形m put (key, value)，
该调用返回一个Option值，其中包含此前与键相关联的值，如果不存在这样的值，则返回None。
 */

//getOrElseUpdate特别适合用于访问用作缓存的映射（Map）。假设调用函数f开销巨大：
def f(x: String) = {
  println("taking my time.")
  x.reverse
}

/*
此外，再假设f没有副作用，即反复以相同参数调用f，得到的结果都相同。那么，我们就可以将之前的调用参数和计算结果保存在一个映射（Map）内，
今后仅在映射中查不到对应参数的情况下实际调用f，这样就可以节约时间。这个映射便可以认为是函数f的缓存。
 */
val cache = collection.mutable.Map[String, String]()

//现在，我们可以写出一个更高效的带缓存的函数f
def cachedF(s: String) = cache.getOrElseUpdate(s, f(s))
cachedF("abc")

/*
注意，getOrElseUpdate的第2个参数是“按名称（by-name）”传递的，
所以，仅当在缓存映射中找不到第1个参数，
而getOrElseUpdate需要其第2个参数的值时，上述的f(“abc”)才会被执行。
当然我们也可以利用Map的基本操作直接实现cachedF，但那样写就要冗长很多了。
 */
def cachedF_Old(arg: String) = cache.get(arg) match {
  case Some(result) => result
  case None =>
    val result = f(arg)
    cache(arg) = result
    result
}

import scala.collection.mutable
import scala.collection.mutable.{Map, SynchronizedMap, HashMap}

object MapMaker {
  def makeMap: Map[String, String] = {
    new HashMap[String, String] with mutable.SynchronizedMap[String, String] {
      override def default(key: String) =
        "Why do you want to know?"
    }
  }
}

val captial = MapMaker.makeMap
captial ++ List("US" -> "Washington", "Paris" -> "France")
captial("US")
captial("Beijing")
captial += ("Beijing" -> "hao")
captial("Beijing")


//同步集合（synchronized set）的创建方法与同步映射（synchronized map）类似。
// 例如，我们可以通过混入SynchronizedSet trait来创建同步哈希集：
val synchroSet =
  new mutable.HashSet[Int] with mutable.SynchronizedSet[Int]

m1.keySet //返回一个Set
for ((k, v) <- m1) yield (v, k) //反转
var scores = scala.collection.immutable.SortedMap("hao" -> 10, "buhao" -> 20) //排序
val months = scala.collection.mutable.LinkedHashMap("jan" -> 1, "feb" -> 2, "mar" -> 3) //按插入顺序访问

"New York".partition(_.isUpper) //输出对偶（“NY”，“ew ork")

//
//拉链zip
//
val symbols = Array("<", "-", ">")
val counts = Array(2, 10, 2)
val pairs = symbols.zip(counts)   //输出Array(("<",2),("-",10),(">",2))
for((s,n)<-pairs) print(s*n)   //输出<<------------>>

symbols.zip(counts).toMap

"Hello".zip("World")

//Map,map,flatmap
val l=List(1,2,3,4,5)
l.map(x=>x*2)

def f(x:Int)= if(x>2) Some(x) else None
l.map(x=>f(x))    //List[Option[Int]]
l.flatMap(x=>f(x))  //List[Int]=List(3,4,5)

def g(v:Int)=List(v-1,v,v+1)
l.map(x=>g(x))   //List[List[Int]]
l.flatMap(x=>g(x))   //List[Int]

val m= Map(1->2,2->4,3->6)
m.toList   //List[(Int,Int)]
m.mapValues(v=>v*2)
m.mapValues(v=>f(v))   //immutable.Map[Int,Option[Int]]
m.flatMap(e=>List(e._2))   //immutable.Iterable[Int]=List(2,4,6)  ，分析就是flat时，把List转换成Map因为是一个数是不成功的
m.flatMap(e=>Iterable(e._2))  //同上，Iterable 默认实现是List
m.flatMap(e=>List(2))  //immutable.Map[Int,Int]=Map(1->2,2->4,3->6)  ，这个List能转换成Map

def h(k:Int, v:Int)= if(v>2) Some(k->v) else None   //Option[(Int,Int)]
m.flatMap(e=>h(e._1,e._2))   //immutable.Map[Int,Int]=Map(2->4,3->6)
/*
Note that we switch to using curly braces, indicating a function block rather than parameters,
and the function is a case statement.
This means that the function block we pass to flatMap is a partialFunction that is only invoked for items that match the case statement,
and in the case statement the unapply method on tuple is called to extract the contents of the tuple into the variables.
This form of variable extraction is very common, and you’ll see it used a lot.
 */
//m.flatMap((k,v)=>h(k,v))   //error
m.flatMap{ case (k,v)=>h(k,v)}   //换成（）就报错了？

m.filter(e=>f(e._2) !=None)
m.filter{ case (k,v) => f(v) != None}
m.filter{ case (k,v) => f(v).isDefined }

val t=(1,2)
t._1

