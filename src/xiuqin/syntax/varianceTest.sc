import scala.collection.immutable.HashSet
import scala.collection.mutable
import scala.collection.immutable

/*
变性 Variance
Scala的类型系统必须同时解释类层次和多态性。
类层次结构可以表达子类关系。在混合OO和多态性时，一个核心问题是：如果T’是T一个子类，
Container[T’]应该被看做是Container[T]的子类吗？变性（Variance）注解允许你表达类层次结构和多态类型之间的关系：


含义	Scala                                    标记
协变  covariant	C[T’]是 C[T] 的子类	           [+T]
逆变  contravariant	C[T] 是 C[T’]的子类	       [-T]
不变  invariant	C[T] 和 C[T’]无关	             [T]

 */

//协变
class Covariant[+A]
val cv:Covariant[AnyRef]=new Covariant[String]

//报错
//val cv1:Covariant[String]=new Covariant[AnyRef]

//逆变
class Contravariant[-A]
val cv2:Contravariant[String]=new Contravariant[AnyRef]

//报错
//val cv3:Contravariant[AnyRef]=new Contravariant[String]

//逆变似乎很奇怪。
// 什么时候才会用到它呢？令人惊讶的是，函数特质的定义就使用了它！
//trait Function1 [-T1, +R] extends AnyRef

class Animal{val sound="rustle"}
class Bird extends Animal{override val sound="call"}
class Chicken extends Bird {override val sound="cluck"}

//Animal不能改成Chicken，因为如果调用时传入Bird对象就不可以了
val getTweet:(Bird=>String)=((a:Animal)=>a.sound)

getTweet(new Chicken)


trait Mammal
trait Dog extends Mammal
trait Cat extends Mammal

val dogs:mutable.HashSet[Dog]
val dogs2:immutable.HashSet[Dog]

//报错：可变类型不具协变
//val mammals:mutable.HashSet[Mammal]=dogs

//val mammals2:immutable.HashSet[Mammal]=dogs2

/*
不可变(Immutable)集合应该是协变的(covariant)。接受容器化类型得方法应该适当地降级(downgrade)集合：

 trait Collection[+T] {
   def add[U >: T](other: U): Collection[U]
 }
可变(mutable)集合应该是不可变的(invariant). 协变对于可变集合是典型无效的。考虑：

 trait HashSet[+T] {
   def add[U >: T](item: U)
 }
 */
