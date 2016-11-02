/**
  * 我们想要从一组语言中汇集不同的程序语言的投票，按照得票的顺序显示(语言，票数)
  */


val votes = Seq(("scala", 1), ("java", 4), ("scala", 10), ("scala", 1), ("python", 10))
val orderedVotes = votes
  .groupBy(_._1)
  .map { case (which, counts) =>
    (which, counts.foldLeft(0)(_ + _._2))
  }.toSeq
  .sortBy(_._2)
  .reverse

//下面的方法实现相同的功能
val votesByLang = votes groupBy { case (lang, _) => lang }
val sumByLang = votesByLang map { case (lang, counts) =>
  val countsOnly = counts map { case (_, count) => count }
  (lang, countsOnly.sum)
}
val orderedVotes2 = sumByLang.toSeq
  .sortBy { case (_, count) => count }
  .reverse

//打印三角形
(1 to 9).map("*" * _).foreach(println)
//找出偶数
(1 to 9).filter(_ % 2 == 0).foreach(println)
//连乘法(二元参数)
(1 to 9).reduceLeft(_ * _) //形如((1*2)*3).....)
//二元参数做排序
"this is xiuqin Liang scalaTest".split(" ").sortWith(_.length < _.length)
//等同于上面的 _.length>_length
"this is xiuqin Liang scalaTest".split(" ").sortBy(_.length)(Ordering[Int].reverse)
val hao = List(1, 4, 2, 5, 3)
hao.init //除最后一个元素
hao.tail //除第一个元素
hao.collect {
  case 1 => println("good")
  case _ => println("bad")
}

hao.reduceLeft((a, b) => a + b) //递归折叠
hao.reduceRight((a, b) => a - b)
hao.reduce((a, b) => a * b)
hao.foldLeft(0)((a, b) => a + b)
hao.foldRight(0)((a, b) => a - b)
hao.fold(0)((a, b) => a + b)

hao.sum
hao.product
hao.max
hao.min
hao.partition(_ % 2 == 0)
hao.span(_ % 2 != 0) //遇到一个不满足的情况直接退出了，注意查看运行结果
hao.splitAt(3) //固定个数
hao.slice(1, 3) //获取分片
hao.mkString(",")
hao.mkString("[", ",", "]")
val buhao = Seq(5, 12, 7, 1)
buhao.permutations //返回迭代器
buhao.combinations(2) //返回组合个数的迭代器

hao.zip(buhao) //匹配按照最短的
hao.zipAll(buhao, 0, 1) //匹配时如果不够设置默认值

val xq = "xiuqin_zipWithIndex"
xq.zipWithIndex

//
//并发集合
//

//会并发求和，par方法会产出当前集合的一个并行实现
(1 until 10000000).par.sum

//par并行化for
for (i <- (0 to 1000000).par) yield i + " "

//并行运算不能混入共享的变量，因为结果无法预知
var count = 0
for (c <- (0 to 1000).par) {
  if (c % 2 == 0) count += 1
} //错误

//reduceLeft和foldRight等不能用于par，对于reduce和fold只有针对
//那些可以自由结合操作才可以
(0 to 1000).par.reduce(_ + _)
(0 to 1000).par.fold(0)(_ + _)

//aggregate将操作符应用于集合的不同部分，然后在用另一个操作符组合出结果
"xiuqin_aggregate".par.aggregate(Set('l','i','a','n','g'))(_ + _, _ ++ _)
//同下
"xiuqin_aggregate".foldLeft(Set('l','i','a','n','g'))(_ + _)