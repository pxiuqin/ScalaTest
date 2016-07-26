/*

 */

abstract class Term

case class Var(name: String) extends Term

case class Fun(arg: String, body: Term) extends Term

case class App(f: Term, v: Term) extends Term

//untyped lambda calculus
val hao = Fun("x", Fun("y", App(Var("x"), Var("y"))))

//accessed directly
val x = Var("x")
x.name

//For every case class the Scala compiler
// generates an equals method which implements
// structural equality and a toString method.
// For instance:
val x1=Var("x")
val x2=Var("x")
val x3=Var("y")
x1==x2
x1==x3

object TermTest{
  def printTerm(term:Term): Unit ={
    term match{
      case Var(n)=>print(n)
      case Fun(x,b)=>print("^"+x+".")
        printTerm(b)
      case App(f,v)=>
        print("(")
        printTerm(f)
        print(" ")
        printTerm(v)
        print(")")
    }
  }

  def isIdentityFun(term:Term):Boolean=term match {
    case Fun(x,Var(y)) if x==y=>true
    case _=>false
  }

  val id=Fun("x",Var("x"))
  val t=Fun("x",Fun("y",App(Var("x"),Var("y"))))
  printTerm(t)
  println()
  println(isIdentityFun(id))
  println(isIdentityFun(t))
}

TermTest.printTerm(Var("x"))
TermTest.isIdentityFun(Var("x"))

