/**
  * sql的处理
  */

/*
def genSql = {

  //
  //列连接成Json格式
  //
  var sql =
    s"""select concat('{"dataid":"',dataid,'","""
  //CONCAT('{"hao":"',table_name,'"}')
  for (col <- columnInfo) {
    sql += s""""${col.name}":"',replace(ifnull(`${col.name}`,"_isnull_"),"\\\"","\\\\\\\""),'","""
  }
  sql = sql.substring(0, sql.lastIndexOf(",")) + """}') as json"""
  sql += s" from `${columnInfo(0).table_name}` "

  //生成查询条件
  //sql += genWhereSql

  sql + ";"
}*/
