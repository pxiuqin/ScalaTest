/**
  *
  */

class Graph {

  class Node {
    val connectedNodes: List[Node] = Nil

    def connectTo(node: Node): Unit = {
      if (connectedNodes.find(node.equals).isEmpty) {
        connectedNodes = node :: connectedNodes
      }
    }
  }

  val nodes: List[Node] = Nil

  def newNode: Node = {
    val res = new Node
    nodes = res :: nodes
    res
  }
}

object GraphTest{
  val g=new Graph
  val g1:Graph=new Graph  //state explicitly
  val n1=g.newNode
  val n2=g.newNode
  val n3=g.newNode
  n1.connectTo(n2)
  n3.connectTo(n1)
}

object IllegalGraphTest{
  val g:Graph=new Graph
  val n1:g.Node=g.newNode
  val n2:g.Node=g.newNode
  n1.connectTo(n2)   //legal
  val h:Graph=new Graph
  val n3:h.Node=h.newNode
  n1.connectTo(n3)    //illegal    but java legal
}

class Graph2{
  class Node{
    val connectedNodes:List[Graph2#Node]=Nil
    def connectTo(node:Graph2#Node): Unit ={
      if(connectedNodes.find(node.equals).isEmpty){
        connectedNodes=node :: connectedNodes
      }
    }
  }

  val nodes:List[Node]=Nil
  def newNode:Node={
    val res=new Node
    nodes=res :: nodes
    res
  }
}