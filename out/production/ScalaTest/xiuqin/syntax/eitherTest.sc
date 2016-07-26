/**
  * Either is just like Option.
  * Right is just like Some.
  * Left is just like None, except you can include content with it to describe the problem.
  */

object EitherLeftRightExample {
  def divideXByY(x: Int, y: Int): Either[String, Int] = {
    if (y == 0) Left("can not divide by 0")
    else Right(x / y)
  }
}

EitherLeftRightExample.divideXByY(1, 0)
EitherLeftRightExample.divideXByY(1, 1)

EitherLeftRightExample.divideXByY(1, 0) match {
  case Left(s) => println("answer:" + s)
  case Right(i) => println("answer:" + i)
}


