import javax.swing.{JFrame, JPanel}

/**
* Created by IntelliJ IDEA.
* User: rxxuzi
* Date: 04-16-23
* Time: 20-19
* */

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
    val jf = new JFrame()
    val jp = new JPanel()
    jf.setVisible(true)
    jf.setDefaultCloseOperation(3)
    jf.setTitle("Balls")
    jf.add {
      new Core()
    }
    jf.setSize(1050, 750)


    System.out.println("Hello Jdk !")

  }
}
