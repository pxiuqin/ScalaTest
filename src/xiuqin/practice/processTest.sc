import scala.sys.process._

// This uses ! to get the exit code
def fileExists(name: String) = Seq("test", "-f", name).! == 0

// This uses !! to get the whole result as a string
val dirContents = "ls".!!

// This "fire-and-forgets" the method, which can be lazily read through
// a Stream[String]
def sourceFilesAt(baseDir: String): Stream[String] = {
  val cmd = Seq("find", baseDir, "-name", "*.scala", "-type", "f")
  cmd.lines
}


import scala.sys.process._

// An overly complex way of computing size of a compressed file
def gzFileSize(name: String) = {
  val cat = Seq("zcat", name)
  var count = 0
  def byteCounter(input: java.io.InputStream) = {
    while(input.read() != -1) count += 1
    input.close()
  }
  val p = cat run new ProcessIO(_.close(), byteCounter, _.close())
  p.exitValue()
  count
}

// This "fire-and-forgets" the method, which can be lazily read through
// a Stream[String], and accumulates all errors on a StringBuffer
def sourceFilesAt2(baseDir: String): (Stream[String], StringBuffer) = {
  val buffer = new StringBuffer()
  val cmd = Seq("find", baseDir, "-name", "*.scala", "-type", "f")
  val lines = cmd lines_! ProcessLogger(buffer append _)
  (lines, buffer)
}