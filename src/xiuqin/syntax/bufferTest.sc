import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/*
Buffers是可变序列一个重要的种类。它们不仅允许更新现有的元素，而且允许元素的插入、移除和在buffer尾部高效地添加新元素。
buffer 支持的主要新方法有：用于在尾部添加元素的 += 和 ++=；
用于在前方添加元素的+=: 和 ++=: ；
用于插入元素的 insert和insertAll；
以及用于删除元素的 remove 和 -=。
ListBuffer和ArrayBuffer是常用的buffer实现 。
顾名思义，ListBuffer依赖列表（List），支持高效地将它的元素转换成列表。
而ArrayBuffer依赖数组（Array），能快速地转换成数组
 */
val lb=ListBuffer(1,2,3,4,5)
val ab=ArrayBuffer(6,7,8,9,10)
//
//Add
//

lb+=100
ab+=101

lb+=(11,12,13,14,15)
ab+=(11,12)

lb++=List(23,24)
ab++=List(100,101)

201+=: lb
201+=: ab

List(102,103)++=: lb

lb.insert(1,10)

lb.insertAll(1,List(10,11))

//
//Removal
//
val hao= lb-=1
val hao1= lb.remove(1)
hao
lb.remove(1,2)
lb.trimStart(1)    //移除前n个数
lb.trimEnd(5)      //移除后n个数
lb.clear()

val v=lb.clone()





