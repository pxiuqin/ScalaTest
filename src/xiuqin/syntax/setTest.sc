import scala.collection.immutable.TreeSet

/*
测试型的方法：contains，apply，subsetOf。contains方法用于判断集合是否包含某元素。
集合的apply方法和contains方法的作用相同，因此 set(elem) 等同于set constains elem。
这意味着集合对象的名字能作为其自身是否包含某元素的测试函数
 */

/*
以下为不可变集合，Immutable.Set
 */
val fruit = Set("apple", "orange", "peach", "banana")
val animal = Set("dog", "pag", "horse")
//
//Tests
//
fruit.contains("apple")
fruit("peach")
fruit("potato")
fruit.subsetOf(animal) //Tests whether xs is a subset of ys.

//
//Additions
//
fruit + "pear"
animal +("bow", "fish", "sheep")
fruit ++ animal
//
//removties
//
fruit - "apple"
animal -("dog", "pag")
fruit -- animal
fruit.empty
//
//BinaryOperations
//
fruit & animal //交集
fruit.intersect(animal) //same as fruit&animal

fruit | animal //并集，等同与++
fruit.union(animal) //same as fruit|animal

fruit &~ animal //差集，等同于--
fruit.diff(animal) //sam as fruit&~animal
/*
以下为可变集合，Mutable.Set
 */
val set1 = scala.collection.mutable.Set(1, 2, 3, 4, 5)
val set2 = scala.collection.mutable.Seq(6, 7, 8, 9, 10)
//
//Adding
//
set1 += 101
set1 +=(101, 102, 103)
set1 ++= set2
//把元素x添加到集合中，如集合xs之前没有包含x，该操作返回true，否则返回false。
set1.add(1001)
//
//Removals
//
set1 -= 101
set1 -=(101, 102, 103)
set1 --= set2
set1.remove(1)
//只保留集合中满足条件p的元素。
set1.retain((x: Int) => x > 4)
set1
set1.clear() //Removes all elements from set1
//
//Update
//
//（ 同 update(x, b) ）参数b为布尔类型，
// 如果值为true就把元素x加入集合xs，否则从集合xs中删除x。
set1(1) = true
set1
set1.update(1, true)
set1
set1.update(1, false)
set1
//
//Cloning
//
val newSet1 = set2.clone()
/*
可变和非可变的一些比较：
与不变集合一样，可变集合也提供了+和++操作符来添加元素，-和--用来删除元素。
但是这些操作在可变集合中通常很少使用，因为这些操作都要通过集合的拷贝来实现。
可变集合提供了更有效率的更新方法，+=和-=。 s += elem，添加元素elem到集合s中，
并返回产生变化后的集合作为运算结果。同样的，s -= elem 执行从集合s中删除元素elem的操作，
并返回产生变化后的集合作为运算结果。
除了+=和-=之外还有从可遍历对象集合或迭代器集合中添加和删除所有元素的批量操作符++=和--=。

 */

/*
选用+=和-=这样的方法名使得我们得以用非常近似的代码来处理可变集合和不可变集合。
先看一下以下处理不可变集合s的REPL会话：
 */

var s1 = Set(1, 2, 3)
s1 += 4
s1 -= 2
s1

/*
我们在immutable.Set类型的变量中使用+=和-= 。
诸如 s += 4 的表达式是 s = s + 4 的缩写，它的作用是，
在集合s上运用方法+，并把结果赋回给变量s。
下面我们来分析可变集合上的类似操作。
 */
val s2 = collection.mutable.Set(1, 2, 3)  //这里有些疑问val是不可变，怎么可变了
s2 += 4
s2 -= 2
s2

/*
总结可变和非可变：

最后结果看起来和之前的在非可变集合上的操作非常相似；
从Set(1, 2, 3)开始，最后得到Set(1, 3, 4)。然而，尽管相似，
但它们在实现上其实是不同的。 这里s += 4 是在可变集合值s上调用+=方法，
它会改变s的内容。同样的，s -= 2 也是在s上调用 -= 方法，也会修改s集合的内容。
通过比较这两种方式得出一个重要的原则。
我们通常能用一个非可变集合的变量来替换可变集合的常量，反之亦然。
这一原则至少在没有别名的引用添加到Collection时起作用。
别名引用主要是用来观察操作在Collection上直接做的修改还是生成了一个新的Collection。

可变集合同样提供作为+=和-=的变型方法，add和remove，
它们的不同之处在于add和remove会返回一个表明运算是否对集合有作用的Boolean值

目前可变集合默认使用哈希表来存储集合元素，非可变集合则根据元素个数的不同，
使用不同的方式来实现。空集用单例对象来表示。
元素个数小于等于4的集合可以使用单例对象来表达，元素作为单例对象的字段来存储。
 元素超过4个，非可变集合就用哈希前缀树（hash trie）来实现。

采用这种表示方法，较小的不可变集合（元素数不超过4）往往会比可变集合更加紧凑和高效。
所以，在处理小尺寸的集合时，不妨试试不可变集合。
*/


/*
集合的两个特质是SortedSet和BitSet
 */

/*
SortedSet 是指以特定的顺序（这一顺序可以在创建集合之初自由的选定）排列其元素（使用iterator或foreach）的集合。
SortedSet 的默认表示是有序二叉树，即左子树上的元素小于所有右子树上的元素。
这样，一次简单的顺序遍历能按增序返回集合中的所有元素。Scala的类 immutable.TreeSet 使用红黑树实现，
它在维护元素顺序的同时，也会保证二叉树的平衡，即叶节点的深度差最多为1。
 */

//创建一个空的TreeSet，可以先定义排序规则：
val myOrdering=Ordering.fromLessThan[String](_>_)
val treeSet=TreeSet.empty(myOrdering)   //默认可以不使用排序规则 TreeSet.empty[String]

treeSet+("one","two","three","four")
treeSet

/*
有序集合同样支持元素的范围操作。
例如，range方法返回从指定起始位置到结束位置（不含结束元素）的所有元素，from方法返回大于等于某个元素的所有元素。
调用这两种方法的返回值依然是有序集合。例如：
 */
treeSet.range("one","two")
treeSet.from("two")

/*
位集合是由单字或多字的紧凑位实现的非负整数的集合。
其内部使用Long型数组来表示。第一个Long元素表示的范围为0到63，第二个范围为64到127，
以此类推（值为0到127的非可变位集合通过直接将值存储到第一个或第两个Long字段的方式，优化掉了数组处理的消耗）。
对于每个Long，如果有相应的值包含于集合中则它对应的位设置为1，否则该位为0。
这里遵循的规律是，位集合的大小取决于存储在该集合的最大整数的值的大小。
假如N是为集合所要表示的最大整数，则集合的大小就是N/64个长整形字，或者N/8个字节，再加上少量额外的状态信息字节。

因此当位集合包含的元素值都比较小时，它比其他的集合类型更紧凑。
位集合的另一个优点是它的contains方法（成员测试）、+=运算（添加元素）、-=运算（删除元素）都非常的高效
 */





