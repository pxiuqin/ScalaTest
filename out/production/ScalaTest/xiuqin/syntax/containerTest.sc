//
//更高级多态性类型 和 特设多态性
//Scala可以对“更高阶”的类型进行抽象。例如，假设您需要用几种类型的容器处理几种类型的数据。你可能定义了一个Container的接口，
//它可以被实现为几种类型的容器：Option、List等。
//你要定义可以使用这些容器里的值的接口，但不想确定值的类型。
//
trait Container[M[_]] {
  def put[A](x: A): M[A]

  def get[A](m: M[A]): A
}
val container = new Container[List] {
  def put[A](x: A) = List(x)

  def get[A](x: List[A]) = x.head
}
container.put("xiqin")
container.put(133)
container.get(List(1, 2, 3))
//如果我们结合隐式转换implicits使用容器，我们会得到“特设的”多态性：即对容器写泛型函数的能力。
implicit val listContainer = new Container[List] {
  def put[A](x: A) = List(x)
  def get[A](x: List[A]) = x.head
}
listContainer.put("liang")

implicit val someContainer = new Container[Some] {
  def put[A](x: A) = Some(x)

  def get[A](x: Some[A]) = x.get
}
someContainer.put(111)

def tupleize[M[_] : Container, A, B](fst: M[A], snd: M[B]) = {
  val c = implicitly[Container[M]]
  c.put(c.get(fst), c.get(snd))
}

tupleize(Some(1),Some(2))
tupleize(Some(1,3),Some(2,4))
tupleize(List(1),List(2))
//tupleize(Some(23,33),List(3))


