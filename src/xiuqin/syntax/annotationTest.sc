/**
  * Java注解并不影响编译器如何生成字节码
  * 它们仅仅是往字节码中添加数据，以便外部工具可以利用到他们
  * 而Scala中注解可以影响到编译过程
  */

//什么可以被注解

import com.sun.istack.internal.NotNull

import scala.annotation.Annotation

//@Entity class Credentials   //注解类
//@Test def testSomeFeature()   //注解方法
//@BeanProperty var username = _   //注解属性
def doSomething(@NotNull message: String) {}

//注解函数参数

//你也可以应用多个注解，先后次序没有影响

//如果给主构造器添加注解，需要把注解放到构造器之前
//并加上一对圆括号
//class Credentials @Inject()(var name: String, var passowrd: String)

//给表达式添加注解,你需要在表达式后加上冒号，然后是注解
//(Map(1->"a",2->"b").get(2):@unchecked) match {}

//给类型参数添加注解
class MyClass[@specialized T]

//针对实际类型的注解应放置在类型名称之后
//String @cps[Unit]   //@cps是一个类型参数，在这里我们为String 类型添加了注解