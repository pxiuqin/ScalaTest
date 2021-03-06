import io.Source
import java.io.PrintWriter

object FileTest {
  val path = "test.txt"

  //1、文件行到转顺序
  def reverseFileLine(): Unit = {
    val reader = Source.fromFile(path).getLines()
    val result = reader.toArray.reverse

    val pw = new PrintWriter(path)
    result.foreach(line => pw.write(line + "\n"))

    pw.close()
  }

  //2、文件内容替换
  def replaceFileContent(): Unit = {
    val reader = Source.fromFile(path).getLines()
    val result = for (line <- reader) yield line.replaceAll("\\t", " ")

    val pw = new PrintWriter(path)
    result.foreach(line => pw.write(line + "\n"))

    pw.close()
  }

  //3、打印字符数大于12的单词
  def printWordCount(): Unit = {
    Source.fromFile("text.txt").mkString.split("\\s").foreach(word => word.length > 12)
  }

  //4、文件中的浮点数之和、平均数、最大值、最小值
  def printFloatStatistic():Unit={
    val nums=Source.fromFile("text.txt").mkString.split("\\s")
    var total=0d
    nums.foreach(total+=_.toDouble)
    println(total)
    println(total/nums.length)
    println(nums.max)
    println(nums.min)
  }

  //5、向文件中写入2的n次方及其倒数，指数n从0到20。对齐各列
  def print2En():Unit={
    val pw=new PrintWriter("text.txt")

    for(n<-0 to 20){
      val t=BigDecimal(2).pow(n)
      pw.write(t.toString())
      pw.write("\t\t")
      pw.write((1/t).toString())
      pw.write("\n")
    }
  }
}