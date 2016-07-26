/*

 */

object FilterTest {
  def filter(xs: List[Int], threshold: Int) = {
    def process(ys: List[Int]): List[Int] =
      if (ys.isEmpty) ys
      else if (ys.head < threshold) ys.head :: process(ys.tail)
      else process(ys.tail)
    process(xs)
  }
}

FilterTest.filter(List(1, 9, 2, 8, 3, 7, 4), 5)