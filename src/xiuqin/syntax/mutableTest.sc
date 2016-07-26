import scala.collection.mutable

/*
可变容器的介绍
 */

/**
  * Array Buffers
  * 一个ArrayBuffer缓冲包含数组和数组的大小。对数组缓冲的大多数操作，
  * 其速度与数组本身无异。因为这些操作直接访问、修改底层数组。
  * 另外，数组缓冲可以进行高效的尾插数据。追加操作均摊下来只需常量时间。
  * 因此，数组缓冲可以高效的建立一个有大量数据的容器，无论是否总有数据追加到尾部
 */
val buf=scala.collection.mutable.ArrayBuffer.empty[Int]
buf+=1
buf+=10
buf.toArray


/**
  * List Buffers
  * ListBuffer 类似于数组缓冲。区别在于前者,其内部实现是链表， 而非数组。
  * 如果你想把构造完的缓冲转换为列表，那就用列表缓冲，别用数组缓冲。
  */

val litBuf=scala.collection.mutable.ListBuffer.empty[Int]
litBuf+=1
litBuf+=10
litBuf.toList


/**
  * StringBuilders
  * 数组缓冲用来构建数组，列表缓冲用来创建列表。类似地，StringBuilder 用来构造字符串。
  * 作为常用的类，字符串构造器已导入到默认的命名空间。
  * 直接用 new StringBuilder就可创建字符串构造器
  */

val sb=new StringBuilder
sb+='a'
sb++="bcedef"
sb.toString()

/**
  * 可变队列
  */

val queue=new mutable.Queue[String]
queue+="a"
queue++=List("b","c")
queue
queue.dequeue()
queue

/**
  * 可变栈
  */

val stack=new mutable.Stack[Int]
stack.push(1)
stack
stack.push(2)
stack
stack.top
stack.pop()
stack

/**
  * Hash Table
  * Hash Table 用一个底层数组来存储元素，每个数据项在数组中的存储位置由这个数据项的Hash Code 来决定。
  * 添加一个元素到Hash Table不用花费多少时间，只要数组中不存在与其含有相同Hash Code的另一个元素。因此，只要Hash Table能够根据一种良好的hash codes分配机制来存放对象，Hash Table的速度会非常快。所以在Scala中默认的可变map和set都是基于Hash Table的。
  * 你也可以直接用mutable.HashSet 和 mutable.HashMap 来访问它们
  */

/**
  * Hash Table的迭代并不是按特定的顺序进行的。它是按任何可能的顺序，依次处理底层数组的数据。
  * 为了保证迭代的次序，可以使用一个Linked Hash Map 或 Set 来做为替代。Linked Hash Map 或 Set 像标准的Hash Map 或 Set一样，
  * 只不过它包含了一个Linked List,其中的元素按添加的顺序排列。
  * 在这种容器中的迭代都是具有相同的顺序，就是按照元素最初被添加的顺序进行迭代。
  */

val map=scala.collection.mutable.HashMap.empty[Int,String]
map+=(1->"make a web site")
map+=(3->"profit!")
map(1)
map.contains(2)

/**
  * Weak Hash Map
  * 是一种特殊的Hash Map，垃圾回收器会忽略从Map到存储在其内部的Key值的链接。
  * 这也就是说，当一个key不再被引用的时候，这个键和对应的值会从map中消失。Weak Hash Map
  * 可以用来处理缓存，比如当一个方法被同一个键值重新调用时，你想重用这个大开销的方法返回值。
  * 如果Key值和方法返回值存储在一个常规的Hash Map里，Map会无限制的扩展，Key值也永远不会被垃圾回收器回收。用Weak Hash Map会避免这个问题。
  * 一旦有一个Key对象不再被引用，那它的实体会从Weak Hash Map中删除。在Scala中，
  * WeakHashMap类是Weak Hash Map的实现类，封装了底层的Java实现类java.util.WeakHashMap。
  */


/**
  * Mutable Bitsets
一个类型为mutable.BitSet的可变bit集合和不可变的bit集合很相似，
它只是做了适当的修改。Mutable bit sets在更新的操作上比不可变bit set 效率稍高，
因为它不必复制没有发生变化的 Long值。
  */

val bits=scala.collection.mutable.BitSet.empty
bits+=1
bits+=3
bits
