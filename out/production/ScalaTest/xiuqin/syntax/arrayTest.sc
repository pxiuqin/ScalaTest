/**
  * 长度固定用Array，若可变ArrayBuffer
  * 提供初始值时不要使用new
  * 用（）来访问元素
  * 用for(elem<-arr)来遍历元素
  * 用for(elem<-arr if ...)...yield... 来将原数组转换为新数组
  * 和java互操作用ArrayBuffer，使用collection.JavaConversions中转换
  *
  *
  * 在Scala中，数组是一种特殊的collection。
  * 一方面，Scala数组与Java数组是一一对应的。
  * 即Scala数组Array[Int]可看作Java的Int[]，
  * Array[Double]可看作Java的double[]，
  * 以及Array[String]可看作Java的String[]。
  * 但Scala数组比Java数组提供了更多内容。
  * 首先，Scala数组是一种泛型。即可以定义一个Array[T]，
  * T可以是一种类型参数或抽象类型。
  * 其次，Scala数组与Scala序列是兼容的 - 在需要Seq[T]的地方可由Array[T]代替。
  * 最后，Scala数组支持所有的序列操作
  *
  * 总结，泛型数组创建需要类声明。所以每当创建一个类型参数T的数组，你还需要提供一个T的隐式类声明。
  * 最简单的方法是声明类型参数与ClassManifest的上下文绑定，如 [T: ClassManifest]
  */

val nums=new Array[Int](10)
val al = Array(1, 2, 3)
val a2 = al.map(_ * 3)
al
val a3 = a2.filter(_ % 2 != 0)
a3.reverse
a3

//可变长度，java有ArrayList;c++有vector;Scala为ArrayBuffer
import scala.collection.mutable.ArrayBuffer
val b=ArrayBuffer[Int]()
b+=1
b++=Array(11,434)

val matrix = Array.ofDim[Double](3, 4) // 3行4列
matrix(1)(2) = 42


//和Seq的转换
/**
  * 注意seq是一个WrappedArray，seq调用reverse方法也会得到一个WrappedArray。
  * 这是没问题的，因为封装的数组就是Seq，在任意Seq上调用reverse方法都会得到Seq。
  * 反之，变量ops属于ArrayOps这个类，对其调用reverse方法得到一个数组，
  * 而不是Seq。
  * 上例直接使用ArrayOps仅为了展示其与WrappedArray的区别，这种用法非常不自然。
  * 一般情况下永远不要实例化一个ArrayOps，而是在数组上调用Seq的方法：
  */
val seq: Seq[Int] = al.toSeq
val a4: Array[Int] = seq.toArray
al.eq(a4)
seq.reverse
al.reverse
val ops: collection.mutable.ArrayOps[Int] = al
ops.reverse
intArrayOps(al).reverse


//类型推断
/**
  * 在Java中你不可以定义一个以T为类型参数的T[]。
  * 那么Scala的Array[T]是如何做的呢？事实上一个像Array[T] 的泛型数组在运行时态可任意为Java的
  * 八个原始数组类型像byte[], short[], char[], int[], long[], float[], double[], boolean[],
  * 甚至它可以是一个对象数组。最常见的运行时态类型是AnyRef ，
  * 它包括了所有的这些类型（相当于java.lang.Object），因此这样的类型可以通过Scala编译器映射到Array[T].
  * 在运行时，当Array[T]类型的数组元素被访问或更新时，就会有一个序列的类型测试用于确定真正的数组类型，
  * 随后就是java中的正确的数组操作。这些类型测试会影响数组操作的效率。
  * 这意味着如果你需要更大的性能，你应该更喜欢具体而明确的泛型数组。
  * 代表通用的泛型数组是不够的，因此，也必然有一种方式去创造泛型数组。
  * 这是一个更难的问题，需要一点点的帮助你。
  * 为了说明这个问题，考虑下面用一个通用的方法去创造数组的尝试。
  */

//报错
/**
  * 这里需要你做的就是通过提供一些运行时的实际元素类型参数的线索来帮助编译器处理。
  * 这个运行时的提示采取的形式是一个`scala.reflect.ClassManifest`类型的类声明。
  * 一个类声明就是一个类型描述对象，给对象描述了一个类型的顶层类。
  * 另外，类声明也有`scala.reflect.Manifest`类型的所有声明，它描述了类型的各个方面。
  * 但对于数组创建而言，只需要提供类声明。
  * @param xs
  * @tparam T
  * @return
  */
/*def evenElems[T](xs:Vector[T]):Array[T]={
  val arr=new Array[T]((xs.length+1)/2)
  for(i<-0 until xs.length by 2)
    arr(i/2)=xs(i)
  arr
}*/

//如果你指示编译器那么做它就会自动的构建类声明。
// “指示”意味着你决定一个类声明作为隐式参数，像这样：
def evenElems1[T](xs: Vector[T])(implicit m: ClassManifest[T]): Array[T] = {
  val arr = new Array[T]((xs.length + 1) / 2)
  for (i <- 0 until xs.length by 2)
    arr(i / 2) = xs(i)
  arr
}

//使用一个替换和较短的语法。通过用一个上下文绑定你也可以要求类型与一个类声明一起。
// 这种方式是跟在一个冒号类型和类名为ClassManifest的后面，想这样：
def evenElems2[T: ClassManifest](xs: Vector[T]): Array[T] = {
  val arr = new Array[T]((xs.length + 1) / 2)
  for (i <- 0 until xs.length by 2)
    arr(i / 2) = xs(i)
  arr
}

evenElems1(Vector(1, 2, 3, 4, 5))
evenElems1(Vector("this", "is", "a", "test"))

/**
  * 证明类型ClassManifest[U]的参数
  * def wrap[U](xs: Array[U]) = evenElems(xs)
  * 这里所发生的是，evenElems 需要一个类型参数U的类声明，但是没有发现。
  * 这种情况下的解决方案是，当然，是为了U的另一个隐式类声明。所以下面起作用了：
  * @param xs
  * @tparam U
  * @return
  */
def wrap[U](xs: Array[U]) = evenElems2(Vector(1, 2, 3, 4))

def wrap1[U: ClassManifest](xs: Array[U]) = evenElems2(Vector(1, 2, 3, 4))
