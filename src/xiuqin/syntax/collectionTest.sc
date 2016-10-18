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
(1 to 9).filter(_ % 2 ==0).foreach(println)

//连乘法(二元参数)
(1 to 9).reduceLeft(_ * _) //形如((1*2)*3).....)

//二元参数做排序
"this is xiuqin Liang scalaTest".split(" ").sortWith(_.length<_.length)

//等同于上面的 _.length>_length
"this is xiuqin Liang scalaTest".split(" ").sortBy(_.length)(Ordering[Int].reverse)

