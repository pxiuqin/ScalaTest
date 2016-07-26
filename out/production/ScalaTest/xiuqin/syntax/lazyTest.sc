/**
  * 延迟加载可以当作是介于val和def的中间状态
  * 懒值并不是没有额外开销，每次访问都会有一个方法被调用，
  * 而这个方法将会以线程安全的方式检查该值是否已被初始化
  */

//在被定义时即被取值
val words1 = scala.io.Source.fromFile("/usr/share/dict/words").mkString

//在被首次使用时取值
lazy val words2 = scala.io.Source.fromFile("/usr/share/dict/words").mkString

//在每一次被使用时取值 ‫
def words3 = scala.io.Source.fromFile("/usr/share/dict/words").mkString
