import javax.swing.JFrame

/**
* Created by IntelliJ IDEA.
* User: rxxuzi
* Date: 04-16-23
* Time: 20-19
* */

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
    val jf = new JFrame("MARS")
    jf.setVisible(true)
    jf.setDefaultCloseOperation(3)
    jf.add {
      new Core()
    }
    jf.setSize(1750, 1200)

    System.out.println("Hello Jdk !")
    println(System.getProperty("java.version"))
  }
}
