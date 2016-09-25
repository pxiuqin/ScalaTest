import scala.reflect.internal.util.StringOps

//主要包括一些常见命令
object BaseTest {
  def intersectTest() = {
    "hello".intersect("helwwww").distinct

    //"hello"被隐式的转换成了StringOps
    "hello".intersect("helwwlo")
  }
}