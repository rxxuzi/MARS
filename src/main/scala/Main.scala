import javax.swing.JFrame

object Main {
   def main(args: Array[String]): Unit = {
   //interface test
      //using JFrame app
      val frame = new JFrame("Hello World")
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
      frame.setSize(300,300)
      frame.setVisible(true)
      frame.add(new GUI.Core())

   }
}