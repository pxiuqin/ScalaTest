/** *
  *
  */

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