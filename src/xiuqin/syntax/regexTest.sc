import scala.util.matching.Regex

//scala.util.matching.Regex

val numPattern="[0-9]+".r
//if contain \ useing """..."""
val wsnum="""\s+[0-9]+\s+""".r
val wordReg="""[a-zA-Z]+""".r
val puncReg="""\p{P}+""".r
val hao = "洁面深 hoaofh,,,,,层清洁sfafafafafaf毛-孔,《防腐剂》？分店fafafa3323232324242飞3草显小五到十岁 28岁的妹子看鱼尾纹机,hao——清不见啦24..2"
var source="99 bottles, 98 bottles,,.o0good-haoshi好吧好haoｈａｏ，，．．．．.hhhafa hafha hhhfahf好ｈａｏ，。有的323！32"
source=hao
println(numPattern.replaceAllIn(source,"1"))
//println(wsnum.replaceAllIn(source,"2"))
//println(wordReg.replaceAllIn(source," "))
println(puncReg.replaceAllIn(source,":"))
//全角正则
//[\-,\/,\|,\$,\+,\%,\&,\',\(,\),\*,\x20-\x2f,\x3a-\x40,\x5b-\x60,\x7b-\x7e,\x80-\xff,\u3000-\u3002,\u300a,\u300b,\u300e-\u3011,\u2014,\u2018,\u2019,\u201c,\u201d,\u2026,\u203b,\u25ce,\uff01-\uff5e,\uffe5]
//^[a-zA-Z0-9\u4e00-\u9fa5]$
/*//for
for(matchString<-numPattern.findAllIn("99 bottles, 98 bottles"))
  println(matchString)
//array
val matches=numPattern.findAllIn("99 bottles, 98 bottles").toArray

//first
val ml=wsnum.findFirstIn("99 bottles, 98 bottles")  //Some(" 98 ")

//部分匹配
numPattern.findPrefixOf("99 bottles, 98 bottles")  //Some(99)
wsnum.findPrefixOf("99 bottles, 98 bottles")  //None

//替换首个匹配项
numPattern.replaceFirstIn("99 bottles, 98 bottles","xiuqi") //xiuqi bottles ..

//替换全部
numPattern.replaceAllIn("99 bottles, 98 bottles","xiuqi") //xiuqi bottles , xiuqi ...

//
//正则表达式组
//
val numitemPattern="([0-9]+) ([a-z]+)".r
val numitemPattern(num,iterm)="99 bottles"

//如果是从多个匹配想中提取分组内容使用for
for(numitemPattern(num,item)<-numitemPattern.findAllIn("99 hao, 98 buhao"))
  println(num+"&"+item)


val pattern = new Regex("(S|s)cala")
val str = "Scala is scalable and cool"

println((pattern findAllIn str).mkString(","))*/

