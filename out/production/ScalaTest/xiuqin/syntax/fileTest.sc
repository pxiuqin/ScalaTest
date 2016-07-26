//
import java.io.{PrintWriter, FileInputStream, File}

import scala.io.Source

//第一个参数可以是字符串或者是java.io.File
//第二个参数不写就是平台缺省的编码
val source=Source.fromFile("","UTF-8")

//
val lineIterator=source.getLines()
for(l<-lineIterator)
  print(l)

val lines=source.getLines().toArray
val linesBuf=source.getLines().toBuffer

//都城一个简单的字符串(文件不是很大时)
val contents=source.mkString

//读取字串，因为Source类扩展自Iterator{Char]
for(c<-source)
  print(c)

//如果你想查看某个字符串但又不处理掉它的话，使用buffered方法
//使用head查看，但同时并不把他当做是已经处理的字符（文件很大时）
val iter=source.buffered
while(iter.hasNext)
  if(iter.head =="预期字符串")
    print(iter.head) //处理
    iter.next()

//快而脏的读取源文件中以空格分开的词法单元
val tokens=source.mkString.split("\\S+")

//转换成数字
val numbers= for(w<-tokens) yield w.toDouble

//或者
val num=tokens.map(_.toDouble)

//读取控制台输入
val age=readInt()

//source有读取非文件源的方法：
val urlData=Source.fromURL("http://baidu.com","UTF-8")
val stringData= Source.fromString("liang xiuqin")
val stdIn=Source.stdin   //从标准输入读取

//使用完source时注意close
source.close()

//Scala并没有提供读取二进制文件的方法。需要使用java类
val file=new File("")
val in=new FileInputStream(file)
val bytes=new Array[Byte](file.length().toInt)
in.read(bytes)
in.close()

//Scala没有提供内建的对写入文件的支持。要写入文本文件，可以使用java.io.PrintWriter
val out=new PrintWriter("")
for(i<-1 to 100) out.println(i)
out.close()

//当你传递数字给printf时，编译器会抱怨你需要把它转换成anyRef
out.printf("%6d %10.2f","22".asInstanceOf[AnyRef],"33".asInstanceOf[AnyRef])
//为了避免麻烦(Console类的printf没有这个问题)
out.printf("%6d %10.2f".format("22","33"))

