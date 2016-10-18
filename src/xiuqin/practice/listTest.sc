import javax.xml.crypto.dsig.keyinfo.KeyValue

//

object ListTest {
  //格式化cmd，使用param中的key-value对替换
  def formatCMDAndParam(cmd: String, param: String): String = {
    var new_cmd = cmd.trim
    var new_param = param.trim

    if (new_param.trim.isEmpty) new_cmd
    else {
      var cmdSeq = new_cmd.split(" ").toSeq

      for (eachCMD <- cmdSeq) {
        if (!eachCMD.contains("\\$")) {
          for (elem <- new_param.split("\n").toList) {
            val keyValue = elem.trim.split("=").toList
            if (eachCMD.equals("$" + keyValue.head.trim)) {
              cmdSeq = cmdSeq.updated(cmdSeq.indexOf(eachCMD), keyValue.tail.head.trim)
            }
            else if (eachCMD.contains("$" + keyValue.head.trim)) {
              cmdSeq = cmdSeq.updated(cmdSeq.indexOf(eachCMD)
                , eachCMD.replace("$" + keyValue.head.trim, keyValue.tail.head.trim))
            }
          }
        }
      }

      cmdSeq.mkString(" ").replace("\\$", "$")
    }
  }

  def nil():Unit= {

  }
}
ListTest.formatCMDAndParam("echo 333$hao $buhao \\$hhfa\\$hf",
  "hao= 10\nbuhao= 20\nhhh=1000")
ListTest.formatCMDAndParam("echo $hao $buhao",
  "   ")
