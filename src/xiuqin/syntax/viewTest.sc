/**
  * 各种容器类自带一些用于开发新容器的方法。例如map、filter和++。
  * 我们将这类方法称为转换器（transformers），喂给它们一个或多个容器，它们就会输入一个新容器。

  * 有两个主要途径实现转换器（transformers）。一个途径叫紧凑法，
  * 就是一个容器及其所有单元构造成这个转换器（transformers）。另一个途径叫松弛法或惰性法（lazy），
  * 就是一个容器及其所有单元仅仅是构造了结果容器的代理，
  * 并且结果容器的每个单元都是按单一需求构造的。
  */

//作为一个松弛法转换器的例子，分析下面的 lazy map操作：
def lazyMap[T, U](coll: Iterable[T], f: T => U) = new Iterable[U] {
  def iterator = coll.iterator.map(f)
}

/**
  * 注意lazyMap构造了一个没有遍历容器coll（collection coll）所有单元的新容器Iterable。
  * 当需要时，函数f 可作用于一个该新容器的迭代器单元。

  * 除了Stream的转换器是惰性实现的外，Scala的其他容器默认都是用紧凑法实现它们的转换器。
  * 然而，通常基于容器视图，可将容器转换成惰性容器，反之亦可。
  * 视图是代表一些基容器但又可以惰性得构成转换器（transformers）的一种特殊容器。

  * 从容器转换到其视图，可以使用容器相应的视图方法。如果xs是个容器，
  * 那么xs.view就是同一个容器，不过所有的转换器都是惰性的。若要从视图转换回紧凑型容器，
  * 可以使用强制性方法。

  * 让我们看一个例子。假设你有一个带有int型数据的vector对象，
  * 你想用map函数对它进行两次连续的操作
  */
val v = Vector(1 to 10: _*) //(1 to 10) 为Range类型了
v.map(_ + 1).map(_ * 2)

/**
  * 在最后一条语句中，表达式v map (_ + 1) 构建了一个新的vector对象，该对象被map第二次调用(_ * 2)而转换成第3个vector对象。
  * 很多情况下，从map的第一次调用构造一个中间结果有点浪费资源。上述示例中，将map的两次操作结合成一次单一的map操作执行得会更快些。
  * 如果这两次操作同时可行，则可亲自将它们结合成一次操作。但通常，数据结构的连续转换出现在不同的程序模块里。融合那些转换将会破坏其模块性。
  * 更普遍的做法是通过把vector对象首先转换成其视图，然后把所有的转换作用于该视图，最后强制将视图转换成vector对象，从而避开出现中间结果这种情况。
  */
(v.view.map(_ + 1).map(_ * 2)).force

// v.view 给出了SeqView对象，它是一个延迟计算的Seq。
// SeqView有两个参数，第一个是整型（Int）表示视图单元的类型。
// 第二个Vector[Int]数组表示当需要强制将视图转回时构造函数的类型。
val vv = v.view

// map的结果是输出SeqViewM(...)的值。实质是记录函数map (_ + 1)应用在vector v数组上的封装。
// 除非视图被强制转换，否则map不会被执行。然而，SeqView 后面的 ‘’M‘’表示这个视图包含一个map操作。其他字母表示其他延迟操作。
// 比如‘’S‘’表示一个延迟的slice操作，而‘’R‘’表示reverse操作。
val h = vv.map(_ + 1)

//现在得到了包含2个map操作的SeqView对象，这将输出两个‘’M‘’： SeqViewMM(...)
val hh = h.map(_ * 2)

//强制操作转换
val hao = hh.force

/**
  * 两个存储函数应用于强制操作的执行部分并构造一个新的矢量数组。这样，没有中间数据结构是必须的。

  * 需要注意的是静态类型的最终结果是Seq对象而不是Vector对象。
  * 跟踪类型后我们看到一旦第一个延迟map被应用，就会得到一个静态类型的SeqViewM[Int, Seq[_]。
  * 就是说，应用于特定序列类型的矢量数组的”knowledge”会被丢失。
  * 一些类的视图的实现需要大量代码，
  * 于是Scala 容器链接库仅主要为一般的容器类型而不是特殊功能（一个例外是数组：将数组操作延迟会再次给予静态类型数组的结果）的实现提供视图
  */

//
//两个理由使用View
//

//1、性能：比如回文，寻找第一百万个字内找回文
def isPalindrome(x: String) = x == x.reverse
def findPalidrome(s: Seq[String]) = s.find(isPalindrome)

val words = Seq[String]("world", "good")
//糟糕的写法
findPalidrome(words.take(1000000))

//使用View，不是一个序列的一百万个元素，它只会构造一个轻量级的视图对象。
// 这样，你无需在性能和模块化之间衡量取舍
findPalidrome(words.view.take(1000000))

//2、适用于遍历可变序列的视图
val arr = (0 to 9).toArray

// 这里给出了一个视图subarr指向从数组arr的第三个元素开始的5个元素组成的子数组。
// 这个视图没有拷贝这些元素，而只是提供了它们的一个映射。
val subarr = arr.view.slice(3, 6) //SeqViewS

//定义一个negate方法
def negate(xs: collection.mutable.Seq[Int]) = for (i <- 0 until xs.length) xs(i) = -xs(i)

//之对其arr中的3到5个元素取反操作
negate(subarr)
arr //Array[Int] = Array(0, 1, 2, -3, -4, -5, 6, 7, 8, 9)


/**
  * 看了这些漂亮的视图应用示例你可能会困惑于为什么怎么还是会有 strict型容器存在了？
  * 一个原因是 lazy型容器性能不总是优于strict型容器的。
  * 对于较小的容器在视图里创建和关闭应用附加开销通常是大于从避免中间数据结构的增益。
  * 一个更重要的原因是，如果延迟操作有副作用，可能导致视图非常混乱。
  */

//val actors=for(i<-1 to 10) yield actor{..}
//(1 to 10).map(i=>actor{...})   //等价

//使用视图
//val actors=for(i<-(1 to 10).view) yield actor{...}

/**
  * 总之，视图是协调性能和模块化的一个强大工具。但为了不被延迟利弊评估方面的纠缠，应该在2个方面对视图进行约束。
  * 要么你将在容器转换器不产生副作用的纯粹的功能代码里使用视图。
  * 要么你将它们应用在所有的修改都是明确的可变容器。最好的规避就是混合视图和操作，创建新的根接口，同时消除片面影响。
  */

//强制懒价值执行,两个map不会产生中间结果，每个元素都是同时执行两个操作
val  powers=(0 until 10000).view.map(_+1).map(1 / _).force



