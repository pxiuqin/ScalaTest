/*
If a sequence is mutable, it offers in addition a side-effecting update method,
 which lets sequence elements be updated.
 As always in Scala, syntax like seq(idx) = elem is just a shorthand for seq.update(idx, elem),
 so update gives convenient assignment syntax for free.
 Note the difference between update and updated. update changes a sequence element in place,
 and is only available for mutable sequences.
 updated is available for all sequences and always returns a new sequence instead of modifying the original.
 */

/*
特性（trait) Seq 具有两个子特征（subtrait） LinearSeq和IndexedSeq。
它们不添加任何新的操作，但都提供不同的性能特点：线性序列具有高效的 head 和 tail 操作，
而索引序列具有高效的apply, length, 和 (如果可变) update操作。
常用线性序列有 scala.collection.immutable.List和scala.collection.immutable.Stream。
常用索引序列有 scala.Array scala.collection.mutable.ArrayBuffer。
Vector 类提供一个在索引访问和线性访问之间有趣的折中。
它同时具有高效的恒定时间的索引开销，和恒定时间的线性访问开销。
正因为如此，对于混合访问模式，vector是一个很好的基础。后面将详细介绍vector。
 */
val list1=List(1,2,3,4,5)
val list2=List(6,7,8,9,10)

//
//Index and lenght
//
list1(1)
list1.apply(1)
list1 apply 1

list1.isDefinedAt(2)
list1 isDefinedAt 6

list1.size
list1.length

list1.lengthCompare(list2.length)

//
//Indexing search
//
//The index of first equal elem
list1.indexOf(4)
list2.indexOf(1)
//The index of last equal elem
list1.lastIndexOf(5)
list2.lastIndexOf(11)
//The first index of xs such that successive elements
// starting from that index form the sequence ys
list1.indexOfSlice(list1)
list1.indexOfSlice(list2)
list1.lastIndexOfSlice(list1)
list1.lastIndexOfSlice((list2))

//The index of the First elem in list that satisfies p
list1.indexWhere((x:Int)=>x>0)
list2.indexWhere((x:Int)=>x>6)
//list1中，从list1(i)开始并满足条件p的元素的最长连续片段的长度。
list1.segmentLength((x:Int)=>x>2,2)

//xs序列中满足p条件的先头元素的最大个数
list1.prefixLength((x:Int)=>x>1)
list1.prefixLength((x:Int)=>x>=1)
list1.prefixLength((x:Int)=>x>=0)

//
//adding
//

100+:list1
list1:+101

//在list1后方追加x，直到长度达到len后得到的序列。
list1.padTo(10,0)

//
//updating
//

//将list1中第i个元素开始的r个元素，替换为list2所得的序列。
list1.patch(2,list2,2)
//将list1中第i个元素替换为x后所得的list1的副本。
list1.updated(-1,10)

list1.indexOf(10)
list1.indexOf(100)

//
//sorting
//
val soretd=list1.sorted

list1.sortWith((x:Int,y:Int)=>x>y)

//将序列list1的元素进行排序后得到的新序列。
// 参与比较的两个元素各自经f函数映射后得到一个结果，
// 通过比较它们的结果来进行排序。
list1.sortBy((x:Int)=>x>3)
//
//reversals
//
list1.reverse

//产生序列xs中元素的反序迭代器。
list1.reverseIterator
//以xs的相反顺序，通过f映射xs序列中的元素得到的新序列。
list1.reverseMap((x:Int)=>x>10)
//
//comparisons
//
list1.startsWith(list1)
list1.startsWith(list2)
list1.endsWith(list2)
list1.contains(list1)
//测试xs序列中是否存在一个与ys相同的连续子序列。
list1.containsSlice(list2)
list1.containsSlice(list1)
//测试序列xs与序列ys中对应的元素是否满足二元的判断式p。
(list1.corresponds(list2))((x:Int,y:Int)=>x>100)


//
// 多集操作
//

//	序列xs和ys的交集，并保留序列xs中的顺序。
list1.intersect(list2)
//序列xs和ys的差集，并保留序列xs中的顺序。
list1.diff(list2)
list1.diff(list1)
list2.diff(list1)

//并集；同xs ++ ys。
list1.union(list2)

//不含重复元素的xs的子序列。
list1.distinct


object ComprehesionTest1{
  def even(from:Int,to:Int):List[Int]=
    for(i<-List.range(from,to) if i% 2==0) yield i
}
ComprehesionTest1.even(0,20)

object ComprehensionTest2{
  def foo(n:Int,v:Int)=
    for(i<-0 until n; j<-i until n if i+j==v) yield Pair(i,j)
}

ComprehensionTest2.foo(20,32)
