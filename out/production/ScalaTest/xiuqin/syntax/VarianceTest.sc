/**
  * 函数的参数和返回值都是讨论一个赋值关系
  * @tparam A
  */


//
//Covariant
//
class Covariant[+A]

val cv: Covariant[AnyRef] = new Covariant[String]

//fail
//val cv2:Covariant[String]=new Covariant[AnyRef]


//
//Contravariant
//
class Contravariant[-A]

val cv3: Contravariant[String] = new Contravariant[AnyRef]

//fail
//val cv4:Contravariant[AnyRef]=new Contravariant[String]


//example
//trait Function1[-T,+R] extends AnyRef

class Animal {
  val sound = "rustle"
}

class Bird extends Animal {
  override val sound = "call"
}

class Chicken extends Bird {
  override val sound = "cluck"
}

/*
标准动物库有一个函数满足了你的需求，但它的参数是Animal。
在大多数情况下，如果你说“我需要一个___，我有一个___的子类”是可以的。
但是，在函数参数这里是逆变的。如果你需要一个接受参数类型Bird的函数变量，
但却将这个变量指向了接受参数类型为Chicken的函数，
那么给它传入一个Duck时就会出错。
然而，如果将该变量指向一个接受参数类型为Animal的函数就不会有这种问题：
 */
val getTweet: (Bird => String) = ((a: Animal) => a.sound)

getTweet(new Bird())
getTweet(new Chicken())
//fail
//val getTweet1:(Bird=>String)=((a:Chicken)=>a.sound)

//fail。这里本身就是理解错误，不是说传递的参数之间关系
//而是说函数在赋值转换时强调的
//getTweet(new Animal())
def hao(h: Bird): String = {
  h.sound
}
def hao2(h: Chicken): String = {
  h.sound
}
def hao3(h: Animal): String = {
  h.sound
}

val jiu: (Bird => String) = hao
val jiu2: (Bird => String) = hao2 //错误
val jiu3: (Bird => String) = hao3


val hatch: (() => Bird) = (() => new Chicken)

//def cacophony[T](things:Seq[T])=things map (_.sound)

//确定类型上界
def biophony[T <: Animal](things: Seq[T]) = things map (_.sound)

/*
类型下界也是支持的，这让逆变和巧妙协变的引入得心应手。List[+T]是协变的；一个Bird的列表也是Animal的列表。
List定义一个操作::(elem T)返回一个加入了elem的新的List。
新的List和原来的列表具有相同的类型：
 */
val flock = List(new Bird, new Bird)
new Chicken :: flock
/*
List 同样 定义了::[B >: T](x: B) 来返回一个List[B]。请注意B >: T，这指明了类型B为类型T的超类。
这个方法让我们能够做正确地处理在一个List[Bird]前面加一个Animal的操作：
 */
new Animal :: flock

