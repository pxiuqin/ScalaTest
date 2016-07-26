/**
  * Scala并没有枚举类型，通过Enumeration来产生出枚举
  */
object TrafficLightColor extends Enumeration {
  //val Red, Yellow, Green = 1
  val Red = Value(0, "name")   //ID,Name
  val Yellow = Value
  //依次加1
  val Green = Value
}

//下面两条返回TrafficLightColor.Red
TrafficLightColor(0)
TrafficLightColor.withName("Red")

//枚举的类型是TrafficLightColor.Value而不是TrafficLightColor，后者是握有这些值的对象

//使用一个类型别名
object TrafficLightColor2 extends Enumeration {
  type TrafficLightColor = Value
  val Red, Yellow, Green = Value
}

import TrafficLightColor2._

def doWhat(color: TrafficLightColor) = {
  if (color == Red) "stop"
  else if (color == Yellow) "hurry up"
  else "go"
}