/**
  * 包有嵌套包和串联包
  */

//嵌套包子包有父包的可见性
package a {
  package b {
    package c {

      class hao {}
    }
  }
}

//串联包
package a.b {
  //a中的包在这里不可见
  package c {
    class hao
  }
}

//放在文件顶部和上述串联包相同
/*package a.b
package c
class hao*/


/**
  * 因为包中不能定义变量和方法，这是jvm的局限性。
  * Scala采用了包对象的概念
  * 使用包对象可以创建一些同有的函数和变量
  */

package object people{
  val defaultName="xiuqin"
}

package people{
  class Person{
    val name=defaultName  //从包对象中拿到的变量
  }
}

//包可见性
package a.b.c.people {
  class Person{
    private[people] def desc="good"   //把desc延伸到了people包中
    private[c] def desc2="very good"  //把desc2延伸到了c包中
  }
}


