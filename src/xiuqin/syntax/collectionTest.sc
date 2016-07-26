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
