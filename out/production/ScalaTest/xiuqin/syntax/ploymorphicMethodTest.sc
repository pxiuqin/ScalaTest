/**
  *
  */

def dup[T](x:T,n:Int):List[T]=
  if(n==0)
    Nil
  else
    x::dup(x,n-1)

dup[Int](3,4)
dup("three",3)