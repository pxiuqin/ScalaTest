/**
Iterator <=> java.util.Iterator
Iterator <=> java.util.Enumeration
Iterable <=> java.lang.Iterable
Iterable <=> java.util.Collection
mutable.Buffer <=> java.util.List
mutable.Set <=> java.util.Set
mutable.Map <=> java.util.Map
mutable.ConcurrentMap <=> java.util.concurrent.ConcurrentMap
  */

import collection.JavaConverters._
import scala.collection.immutable.HashMap
import scala.collection.mutable.ArrayBuffer

val jul: java.util.List[Int] = ArrayBuffer(1, 2, 3)
val buf: Seq[Int] = jul

val m:java.util.Map[String,Int]=HashMap("abc"->1,"hello"->2)


//还有一些Scala容器类型可以转换成对应的Java类型，但是并没有将相应的Java类型转换成Scala类型的能力，它们是：
/*Seq => java.util.List
mutable.Seq => java.util.List
Set => java.util.Set
Map => java.util.Map
*/
val h:Seq=java.util.List

//因为Java并未区分可变容器不可变容器类型，
// 所以，虽然能将scala.immutable.List转换成java.util.List，但所有的修改操作都会抛出“UnsupportedOperationException”。参见下例：
