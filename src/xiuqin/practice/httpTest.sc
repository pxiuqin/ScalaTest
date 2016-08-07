/*
import java.security.Principal

import org.apache.http.auth.AuthScope
import org.apache.http.auth.Credentials
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient

import scala.io.Source

def get(url: String) = scala.io.Source.fromURL(url).mkString

def getRestContent(url:String): String = {
  val httpClient = new DefaultHttpClient
  httpClient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new Credentials {override def getUserPrincipal: Principal = ???

    override def getPassword: String = ???
  });
  val httpGet=new HttpGet(url)
  val httpResponse = httpClient.execute(httpGet)
  val entity = httpResponse.getEntity
  var content = ""
  if (entity != null) {
    val inputStream = entity.getContent
    content = Source.fromInputStream(inputStream).getLines.mkString
    inputStream.close
  }
  httpClient.getConnectionManager.shutdown
  return content
}*/
