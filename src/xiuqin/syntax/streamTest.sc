//流是一个尾部被懒计算的不可变列表——也就是说只有当你需要时它才会被计算

def numsFrom(n: BigInt): Stream[BigInt] = n #:: numsFrom(n + 1)
val tenOrMore = numsFrom(10)
tenOrMore.tail
tenOrMore.tail.tail
tenOrMore.tail.tail.tail

val squares = numsFrom(1).map(x => x * x)