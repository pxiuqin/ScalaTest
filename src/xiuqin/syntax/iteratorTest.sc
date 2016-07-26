/**
  * 迭代器不是一个容器，更确切的说是逐一访问容器内元素的方法。
  * 迭代器it的两个基本操作是next和hasNext。
  * 调用it.next()会返回迭代器的下一个元素，并且更新迭代器的状态
  */


/**
  * Scala为Traversable, Iterable和Seq类中的迭代器提供了许多类似的方法。比如：这些类提供了foreach方法以便在迭代器返回的每个元素上执行指定的程序。
  * 使用foreach方法可以将上面的循环缩写为：
  */

// it foreach println

/**
  * 与往常一样，for表达式可以作为foreach、map、withFilter和flatMap表达式的替代语法，
  * 所以另一种打印出迭代器返回的所有元素的方式会是这样：
  */

// for(elem<-if) println(elem)

/**
  * 迭代器和traversable容器中嗲用foreach方法最大区别是：迭代器next到最后一个元素
  * 容器，再调用会抛出异常。与此不同的是，当在容器中调用foreach方法后，容器中的元素数量不会变化（除非被传递进来的函数删除了元素，
  * 但不赞成这样做，因为这会导致意想不到的结果）
  */

val it = Iterator("a", "number", "of", "words")
val length = it.map(_.length)
length.foreach(println)
//it.next()   //出错
val res = it.dropWhile(_.length < 2)
//val largeOf2 = res.next()   //出错
/**
  * 总的来说，如果调用完迭代器的方法后就不再访问它，
  * 那么迭代器的行为方式与容器是比较相像的。
  * Scala容器库中的抽象类TraversableOnce使这一特质更加明显，
  * 它是 Traversable 和 Iterator 的公共父类。顾名思义，
  * TraversableOnce 对象可以用foreach来遍历，
  * 但是没有指定该对象遍历之后的状态。
  * 如果TraversableOnce对象是一个迭代器，它遍历之后会位于最后一个元素，
  * 但如果是Traversable则不会发生变化。TraversableOnce的一个通常用法是作为一个方法的参数类型，
  * 传递的参数既可以是迭代器，也可以是traversable。Traversable类中的追加方法++就是一个例子。
  * 它有一个TraversableOnce 类型的参数，
  * 所以你要追加的元素既可以来自于迭代器也可以来自于traversable容器。
  */

/**
  * 带缓存
  */

//第一个元素不能被检查
def skipEmptyWordsNOT(it: Iterator[String]) =
  while (it.next.isEmpty) {}

//添加了缓存
def skipEmptyWordsNOT2(it: BufferedIterator[String]) =
  while (it.head.isEmpty) {
    it.next()
  }

//Iterator转换成BufferedIterator
val it2=Iterator(1,2,3,4,5)
val bit=it2.buffered
bit.head
bit.next()
bit.next()

// Duplicate the arguments array.
val myArgs = new Array[String](args.length) ;

// Copy method 1:
for (i <- 0 until args.length)
  myArgs.update(i,args(i))

// Syntactic sugar:
// e1(e2) = e3
//    ==>
// e1.update(e2,e3)

// Copy method 2:
for (i <- 0 until args.length)
  myArgs(i) = args(i)

// Copy method 3:
(0 until args.length).foreach(i => myArgs(i) = args(i))

// Copy method 4:
(0 until args.length) foreach (i => myArgs(i) = args(i))


// o.m(a) == o m a
val a = 3
val b = 4
val n = a.+(b)
println(n)
// Prints:
// 7


// Create new array of chars:
val alphabet = Array("a","b","c")

val alphabetChars : Array[String] = Array[String]("a","b","c")


// Create a list:
val numbers = List(42,1701,13)
numbers foreach println
// Prints:
// 42
// 1701
// 13

// Create a new list:
val moreNumbers = 1981 :: numbers

numbers
// Prints:
// 42
// 1701
// 13

moreNumbers foreach println
// Prints:
// 1981
// 42
// 1701
// 13

def myMap[A,B] (f : A => B) (list : List[A]) : List[B] =
  if (list.isEmpty)
  { Nil }
  else
  { f(list.head) :: myMap (f) (list.tail) }


(myMap ((n:Int) => n + 1) (moreNumbers)) foreach println
// Prints:
// 1982
// 43
// 1702
// 14


moreNumbers.map(n => n + 1) foreach println
// Prints:
// 1982
// 43
// 1702
// 14


moreNumbers map (_ + 1) foreach println
// Prints:
// 1982
// 43
// 1702
// 14


class Ship(val x : Int, val y : Int) extends Iterable[(String,Int)] {

  def elements = new Iterator[(String,Int)] {
    private var nextVar = 'x

    def next : (String,Int) = nextVar match {
      case 'x => { nextVar = 'y ; ("x",x) }
      case 'y => { nextVar = 'z ; ("y",y) }
    }

    def hasNext : Boolean = nextVar != 'z
  }

}


val Galactica = new Ship(42,1701)

for ((coord,value) <- Galactica) {
  println(coord + ": " + value)
}
// Prints:
// x: 42
// y: 1701


