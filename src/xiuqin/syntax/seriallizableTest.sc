//java序列化
/*
public class Person implements java.io.Serializable {
  private static final long serialVersionUID=42L;
}*/

//scala序列化
@SerialVersionUID(42L)
class Person extends Serializable


//序列化和反序列化
val fred = new Person {}

import java.io._

val out = new ObjectOutputStream(new FileOutputStream("/tmp/test.obj"))
out.writeObject(fred)
out.close()
val in = new ObjectInputStream(new FileInputStream("/tmp/test.obj"))
val saveFred = in.readObject().asInstanceOf[Person]