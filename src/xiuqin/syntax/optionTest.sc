/*
Option 操作支持泛型，Some("xiuqin" 的类型为Option[String]
 */

//Map类的get方法返回一个Option，如果没有对应
//的键则get返回None
val mapData = Map("xiu" -> 1, "qin" -> 2, "liang" -> 3)
mapData.get("xiu") match {
  case Some(source) => println(source)
  case None => println("no source")
}

//当然你可以这样
val allieSource = mapData.get("xiu")
if (allieSource.isEmpty) println(("no source"))
else println(allieSource.get)

//当然用getOrElse更好
println(mapData.getOrElse("xiuqin", "no source"))

//如果你想略过None值，可以用for推导式：
for (score <- mapData.get("qin")) println(score) //如果get方法返回None，什么也不做

//当然你也可以将Option当作是一个要么为空，要么带有单个元素的集合，
//并使用诸如map|foreach或filter
mapData.get("qin").foreach(println(_))  //如果get返回None时什么也不做
