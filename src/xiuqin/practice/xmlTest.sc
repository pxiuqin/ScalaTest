/** *
  *
  */

import java.io.{InputStreamReader, FileInputStream}
import java.net.URL

import scala.xml._

//注意和x < y 的区别，删除其空格
val doc =
  <body>
    <h1>Hello world</h1>
    <p>
      <a href="scala-lang.org">Scala</a>
      talks XHTML</p>
  </body>;

for (n <- doc.child) println(n.toString())

//NodeBuffer是一个Seq[Node],它可以隐式的转换为NodeSeq
val items = new NodeBuffer
items += <li>xiuqin</li>
items += <li>liang</li>
val nodes: NodeSeq = items

//获取属性
val page =
  <html>
    <head>
      <title>Hello XHTML world</title>
    </head>
    <body>
      <h1>Hello world</h1>
      <p>
        <a href="scala-lang.org">Scala</a>
        talks XHTML</p>
    </body>
  </html>;
println(page.toString())
val url = page.attributes("href").text
val url2 = page.attributes.get("href").getOrElse(Text("")) //返回Option

//自动赋值
val df = java.text.DateFormat.getDateInstance()
val dateString = df.format(new java.util.Date())
def theDate(name: String) =
  <dateMsg addressedTo={name}>
    Hello,
    {name}
    ! Today is
    {dateString}
  </dateMsg>;

println(theDate("John Doe").toString())

//
//内嵌表达式(这里注意插入的是字符串而不是Text())
//
val doc2 = <ul>
  <li>
    {items(0)}
  </li> <li>
    {items(1)}
  </li>
</ul>

val doc3 = <ul>
  {for (i <- items) yield <li>
    {i}
  </li>}
</ul>

  //在属性中使用表达式
    <img src={items(0)}/>

  //在属性中包含一个实体属性
    <a id={new Atom(1)}/> //注意如果属性是null或None是不会设置该值的

//CDATA处理,非xml格式的文本
val code = "alert('code')"
val js = <script>
  {PCData(code)}
</script>


//在Unparsed节点中包含任意文本，原样保留
//val n1 = <xml:unparsed><&></xml:unparsed>
val n2 = Unparsed("<&>")


//加载
import scala.xml.XML

val root = XML.loadFile("myfile.xml")
val root2 = XML.load(new FileInputStream("myfile.xml"))
val root3 = XML.load(new InputStreamReader(new FileInputStream("myfile.xml"), "UTF-8"))
val root4 = XML.load(new URL("baidu.com"))

//保存
XML.save("myfile.xml", root)
