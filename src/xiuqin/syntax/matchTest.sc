/*

 */

object MatchTest1{
  def matchTest(x:Int):String= x match{
    case 1=>"one"
    case 2=>"two"
    case _=>"many"
  }
}

MatchTest1.matchTest(10)

object MatchTest2 {
  def matchTest(x:Any):Any=x match {
    case 1=>"one"
    case "two"=>2
    case y:Int=>"scala.Int"
  }
}

MatchTest2.matchTest("two")