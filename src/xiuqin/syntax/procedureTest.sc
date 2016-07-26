/**
  *对于不返回值的函数有特殊的表示法，如果函数体包含在花括号中但没有前面的=号
  * 那么返回类型就是Unit。这样的函数被称为过程，过程不返回值，我们调用它仅仅是
  * 为了它的副作用
  */

def box(s: String) { // 这里没有=号
var border = "-" * s.length + "--\n"
  println(border + "|" + s + "|\n" + border)
}
