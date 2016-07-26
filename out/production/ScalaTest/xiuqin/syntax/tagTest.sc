import scala.reflect.ClassTag

//different of the classOf and getClass
class A
val a=new A

//this tow type is equal
a.getClass()     //subclass of Class[A]
classOf[A]       //Class[A]
a.getClass()==classOf[A]

val listClass=classOf[List[_]]
val mapIntString=classOf[Map[Int,String]]

//type and class
classOf[List[Int]]==classOf[List[String]]
//typeOf[List[Int]]==typeOf[List[String]]

//ClassTag[T] save orign class T
def mkArray[T:ClassTag](elems:T*)=Array[T](elems:_*)

//TypeTag save detail type T
import scala.reflect.runtime.universe._
def paramInfo[T](x:T)(implicit tag:TypeTag[T]):Unit={
  val targs=tag.tpe match{case TypeRef(_,_,args)=>args}
  println(s"type of $x has type arguments $targs")
}

paramInfo(42)
paramInfo(List(1,2))



