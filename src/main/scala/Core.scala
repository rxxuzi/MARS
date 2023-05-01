import java.awt.event.{KeyAdapter, KeyEvent, MouseEvent, MouseListener, MouseMotionListener}
import java.awt.{Color, Font, Graphics}
import javax.swing.JPanel
import scala.collection.mutable.ArrayBuffer

class Core() extends JPanel {
   
   //Ball classのList
   private final val waves = ArrayBuffer[Wave]()

   //StartTime
   private final val startTime = System.currentTimeMillis()
   private final var lastTime = startTime
   //Ballの半径
   private final val radius = 25
   //SleepTime
   private final val sleepSpeed = 1
   private val interval = 1000
   //clickCount
   private final var clickCount = 1

   private var mx = 300
   private var my = 300 // マウスの座標

   this.setFocusable(true)
   this.setBackground(Color.black)
   this.addMouseListener(new ML())
   this.addMouseMotionListener(new MML())
   this.addKeyListener(new KA())


   override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)
      draw(g)
      val nowTime = System.currentTimeMillis()
      g.setColor(Color.yellow)
      g.setFont(new Font("Serif", Font.BOLD, 20))
      g.drawString((nowTime - startTime) + "ms", 10, 20)
      g.drawString(getWidth + ", " + getHeight, 10, 40)
      g.drawString("Circle :" + waves.size, 10, 60)
      g.drawString("max Radius :" + Wave(0,0,0,0).maxRadius + "px", 10, 80)


   }

   private def draw(g: Graphics): Unit = {
      val nowTime = System.currentTimeMillis()
      if (nowTime - lastTime > interval) {
//         println(waves.length)
         lastTime = nowTime
      }
      g.setColor(Color.white)

      //indicesを使うことでremoveできるようになる
      for(i <- waves.indices){
         waves(i).draw(g)
      }

      //IndexOutOfBoundsExceptionを回避するためにtry catch
      try {
         for(i <- waves.indices){
            if(waves(i).W.delete){
               waves.remove(i)
            }
         }
      }catch {
         case _: Exception =>
      }

      sleep()
   }

   private def sleep(): Unit = {
      Thread.sleep(sleepSpeed)
      repaint()
   }

   //Mouse MotionListener class
   private class MML extends  MouseMotionListener {
      override def mouseDragged(e: MouseEvent): Unit = {
         mx = e.getX - radius
         my = e.getY - radius
         waves += Wave(e.getX , e.getY , clickCount , System.currentTimeMillis())
      }
      override def mouseMoved(e: MouseEvent): Unit = {
         mx = e.getX - radius
         my = e.getY - radius
      }
   }

   //Mouse Listener class
   private class ML() extends MouseListener{
      override def mouseClicked(e: MouseEvent): Unit = {
//         println("clicked")
         waves += Wave(e.getX , e.getY , clickCount , System.currentTimeMillis())
         clickCount += e.getClickCount
      }
      override def mousePressed(e: MouseEvent): Unit = {}
      override def mouseReleased(e: MouseEvent): Unit = {}
      override def mouseEntered(e: MouseEvent): Unit = {}
      override def mouseExited(e: MouseEvent): Unit = {}
   }

   private class KA extends KeyAdapter{
      override def keyTyped(e: KeyEvent): Unit = {
         println(e.getKeyChar)
      }

      override def keyPressed(e: KeyEvent): Unit = super.keyPressed(e)

      override def keyReleased(e: KeyEvent): Unit = super.keyReleased(e)
   }


}
