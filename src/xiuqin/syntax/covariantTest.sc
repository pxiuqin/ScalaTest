/**
  * 综合协变，逆变，上界，下界
  * 首先明白参数和返回值的逆协变，然后想
  * 逆变不能无限向上，所以设置上界：新类型<:上界   -
  * 协变不能无限向下，所以设置下界：新类型>:下界   +
  */


class Animal {}

class Bird extends Animal {}

class Consumer[-S, +T]() {
  //协变，下界, 返回值
  def m1[U >: T](u: U): T = {
    new T
  }

  //逆变，上界，参数
  def m2[U <: S](s: S): U = {
    new U
  }
}

class Test {
  val c: Consumer[Animal, Bird] = new Consumer[Animal, Bird]()
  val c2: Consumer[Bird, Animal] = c
  c2.m1(new Animal)
  c2.m2(new Bird)
}
