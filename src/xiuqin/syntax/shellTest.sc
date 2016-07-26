//scala和shell互操作
import sys.process._

//程序成功执行返回0,否则为非0错误码
val result="ls -all" !

//输出以字符串的形式返回
val result2="ls -all" !!


