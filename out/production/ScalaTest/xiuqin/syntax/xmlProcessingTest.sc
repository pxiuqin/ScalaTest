/*

 */

object XMLTest1 {
  val page =
    <html>
      <head>
        <title>Hello xHtml</title>
      </head>
      <body>
        <h1>hello world</h1>
        <p>
          <a href="scala-lang.org">talks xHtml</a>
        </p>
      </body>
    </html>
}

XMLTest1.page
object XMLTest2 {
  import scala.xml._
  val df = java.text.DateFormat.getDateInstance()
  val dateString = df.format(new java.util.Date())
  def theDate(name: String) =
    <dateMsg addressedTo={name}>
      Hello.{name}! Today is {dateString}
    </dateMsg>
}
XMLTest2.theDate("good")