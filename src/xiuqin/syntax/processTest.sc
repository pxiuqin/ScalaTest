import java.io.File
import java.net.URL

import sys.process._

//执行结果被打印到标准输出，实质是将字符串隐式转换
//成了ProcessBuilder对象，其结果返回0或非0
"ls -al" !

val result="ls -al" !!  //返回字符串形式

"ls -al" #| "grep Docu" !  //以管道的形式传递到下一个程序中

//输出重定向
"ls -al" #> new File("output.txt")

//尾部追加重定向
"ls -al" #>> new File("output.txt")

//URL重定向输入
"grep scala" #< new URL("http://scala.lang.com")

//可以将进程结合在一起使用，比如p #&& q （表示如果p成功了，则执行q）
//以及p #|| q (如果p不成功，则执行q)

//设置执行目录和环境变量(多个环境变量直接添加爱（）)
val p=Process("cd ..", new File("/home/lxq/"), ("LANG","zh_CH"))