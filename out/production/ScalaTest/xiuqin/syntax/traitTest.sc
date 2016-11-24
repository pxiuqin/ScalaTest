#!/bin/bash
scala $0 $@
exit
!#

import java.awt.image.ShortLookupTable
import java.io.PrintStream


/*
 Traits allow multiple inheritance is Scala.

 The order of inheritance is important!
 */


class Animal {
  def printName() {
  }
}


trait Man extends Animal {
  def height = 6;

  override def printName() {
    super.printName();
    println("Man");
  }
}


trait Bear extends Animal {
  val color = 'brown;

  override def printName() {
    super.printName();
    println("Bear");
  }
}


trait Pig extends Animal {
  val shape = 'round;

  override def printName() {
    super.printName();
    println("Pig");
  }
}


class ManBearPig extends Man with Bear with Pig

class BearManPig extends Bear with Man with Pig

class PigBearMan extends Pig with Bear with Man

class PigManBear extends Pig with Man with Bear

class ManPigBear extends Man with Pig with Bear

class BearPigMan extends Bear with Pig with Man

(new ManBearPig).printName;
// Prints:
// Man
// Bear
// Pig

(new BearPigMan).printName;
// Prints:
// Bear
// Pig
// Man

(new Animal with Man with Bear).printName;

// Prints:
// Man
// Bear

//
//叠加在一起的特质
//
trait Logged {
  def log(msg: String) {} //这里不是抽象方法，所以重写需要override
}

class Account

class SavingAccount extends Account with Logged {
  def withdraw(amout: Double): Unit = {
    //to do
    log("xiuqin withdraw") //试想这里这个log方法可是没有实现啊！
  }
}

trait ConsoleLogger extends Logged {
  override def log(msg: String) {
    println(msg)
  }
}

val acct = new SavingAccount with ConsoleLogger
acct.withdraw(2.01)

//log可用，因为ConsoleLogger混入了

//当然你可以给其混入其他的实现
//val acct2=new SavingAccount with FileLogger

//
//特质的wit顺序不同的效果
//

trait TimestampLogger extends Logged {
  override def log(msg: String) {
    super.log(new java.util.Date() + " " + msg)
  }
}

trait ShortLogger extends Logged {
  val maxLength = 15

  override def log(msg: String) {
    super.log(
      if (msg.length <= maxLength) msg else msg.substring(0, maxLength - 3) + "..."
    )
  }
}

//super.log调用的是特质层级中的下一个特质，具体是哪一个，要根据特质添加的顺序来决定。
//一般来说，特质从最后一个开始被处理
val acct11 = new SavingAccount with ConsoleLogger with TimestampLogger with ShortLogger
//Sun Feb 06 17:45:45 ICT 2011 Insufficient..


val acct22 = new SavingAccount with ConsoleLogger with ShortLogger with TimestampLogger

//Sun Feb 06 1...

//
//对特质而言，无法从源码判断super.someMethod会执行哪里的方法。
//确切的方法依赖于使用这些特质的对象或类给出的顺序。这使得super相比在
//传统的继承关系中要灵活得多。

//
//如果你需要控制具体是那一个特质的方法被调用，则可以是方括号中给
//出名称;super[ConsoleLogger].log(...)。这里给出的类型必须是直接超类型；
//你无法使用继承层级中更远的特质或类。


//
//在特质中重写抽象方法
//
trait Logger1 {
  def log(msg: String) //这里是个抽象方法
}

trait TimestempLogger1 extends Logger1 {
  /*override def log(msg:String){  //重写抽象方法
    super.log("")    //super.log定义了吗
  }*/

  //必须是abstract以及override。才能在混入一个具体的log方法
  abstract override def log(msg: String) {
    super.log("")
  }
}


//
//当作富接口使用的特质
//
trait LoggerBase {
  def log(msg: String)

  def info(msg: String) {
    log("INFO:" + msg)
  }

  def severe(msg: String) {
    log("SEVERE:" + msg)
  }

  def warn(msg: String) {
    log("WARN:" + msg)
  }
}

//特质中使用具体和抽象方法，在使用类中实现
class SavingsAccount2 extends Account with LoggerBase {
  def withdraw(amout: Double): Unit = {
    if (amout > 100) severe("xiuqin")
  }

  override def log(msg: String) {
    print(msg)
  }
}


//
//特质的构造顺序
//
//调用超类的构造器
//特质构造器在超类构造器之后，类构造器之前
//特质由左到右被构造
//每个特质当中，父特质先被构造
//如果多个特质共有一个父特质，而那个父特质已经被构造，则不会被再次构造
//所有特质构造完毕，子类被构造

//比如:class SavingAccount3 extends Account with FileLogger with ShortLogger
//Account(超类）
//Logger（第一个特质的父特质）
//FileLogger（第一个特质）
//ShortLogger（第二个特质，注意它的父特质Logger已被构造）
//SavingsAccount（类）

//
//构造器的顺序是类的线性化的反向
//
//线性化是描述某个类型的所有超类型的一种技术规格，按照以下规则定义：
//如果 C extends C1 with C2 with ... with Cn,则 lin(C)=C>>lin(Cn)>>...>lin(C2)>>line(C1)
//在这里，>>的意思是“串接并去掉重复项，右侧胜出”。类如：
//lin(SavingAccount3)
//=SavingAccount>>lin(ShortLogger)>>lin(FileLogger)>>lin(Account)
//=SavingAccount>>(ShortLogger>>Logger)>>(FileLogger>>Logger)>>Lin(Account)
//=SavingAccount>>ShortLogger>>FileLogger>>Logger>>Account
//线性化给出了在特质中super被解析的顺序。举例来说，在ShortLogger中调用super会执行FileLogger的方法
//在FileLogger中调用super会执行Logger的方法


//
//特质不能有构造参数，每个特质都有一个无参数的构造器
//缺少构造器参数是特质和类之间唯一的技术差别。
//

//如果有这样的需求，怎么处理？
//比如：
//val acct10=new SavingAccount with FileLogger("hhh.log")  //错误构造器不能有参数

trait FileLogger {}

//重写FileLogger中的字段fileName
val acct110 = new SavingAccount with FileLogger {
  //这样行不通，问题是构造顺序上。FileLogger构造器先于子类构造器执行。
  val filename = "xiuqin.log"
}

//一个可行的方法(提前定义)
val acct12 = new {
  val filename = "xiuqin.log"
} with SavingAccount with FileLogger

//在类中做同样的事情
class SavingsAccount3 extends {
  val filename = "xiuqin.log"
} with Account with FileLogger {
  //类的实现
}

trait Logger {}

//另一个解决方法在FileLogger构造器中使用懒值
trait FileLogger1 extends Logger {
  val filename: String
  lazy val out = new PrintStream(filename)

  //使用时才会初始化，且每次使用前的都会检查是否初始化，所有不高效
  def log(msg: String) {
    out.println(msg)
  } //不需要重写override
}


//
//型变
//
//def makeFriends(p:Pair[Person])
//如果Student是Person的子类，那么可以是使用Pair[Student]作为函数的参数吗
//缺省情况下这是个错误，虽然Student是Person的子类，但Pair[Student]和Pair[Person]
//没有任何关系

//如果你想要这样的关系，则必须定义Pair类型时表明这一点
class Pair11[+T](val first: T, val second: T)

//+表示该类型是与T类型协变的
//也就是他与T按同样的方向型变，所有上述Pair[Student]是Pair[Person]的子类

//逆变的场景
//考虑泛型类型Friend[T]，表示希望与类型T的人成为朋友的人
trait Friend[-T] {
  def befriend(someone: T)
}

class Person extends Friend[Person] {
  override def befriend(someone: Person) = {
  }
}

class Student extends Person

val xiuqin = new Student
val liang = new Person

//定义一个这样的方法
def makeFriendWith(s: Student, f: Friend[Student]) = {
  f.befriend(s)
}

//试想一下 makeFriendWith(xiuqin,liang) 能成功吗，看上去应该能成功才对
//如果xiuqin愿意和任何人成为朋友，一定也会想和liang成为朋友
//这里注意类型变化的方向和子类型方向是相反的，Student是Person的子类型，
//Friend[Student]是Friend[Person]的父类型，这种情况应该把类型参数声明为逆变
//如上面的Friend[-T]

//对于单参数函数






