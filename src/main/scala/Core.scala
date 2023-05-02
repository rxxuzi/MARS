import java.awt.event.{KeyAdapter, KeyEvent, KeyListener, MouseEvent, MouseListener, MouseMotionListener}
import java.awt.{Color, Font, Graphics}
import javax.swing.JPanel
import scala.collection.mutable.ArrayBuffer

class Core() extends JPanel with KeyListener{
   
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
   private final var moveMode = false

   private var mx = 300
   private var my = 300 // マウスの座標

   this.setFocusable(true)
   this.setBackground(Color.black)
   this.addMouseListener(new ML())
   this.addMouseMotionListener(new MML())
   this.addKeyListener(KA())


   override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)
      draw(g)
      val nowTime = System.currentTimeMillis()
      g.setColor(Color.white)
      g.setFont(new Font("Serif", Font.BOLD, 20))
      g.drawString((nowTime - startTime) + "ms", 10, 20)
      g.drawString(getWidth + ", " + getHeight, 10, 40)
      g.drawString("Wave :" + waves.size, 10, 60)
//      g.drawString("max Radius :" + Wave(0,0,200,0).maxRadius + "px", 10, 80)


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
   //描画更新
   private def sleep(): Unit = {
      Thread.sleep(sleepSpeed)
      repaint()
   }

   //Mouse MotionListener class
   private class MML extends  MouseMotionListener {
      override def mouseDragged(e: MouseEvent): Unit = {
         mx = e.getX - radius
         my = e.getY - radius
         if (!moveMode) {
            waves += Wave(e.getX , e.getY , 300 , System.currentTimeMillis())
         }
      }
      override def mouseMoved(e: MouseEvent): Unit = {
         mx = e.getX - radius
         my = e.getY - radius
         if (moveMode) {
            waves += Wave(e.getX , e.getY , 300 , System.currentTimeMillis())
         }
      }
   }

   //Mouse Listener class
   private class ML() extends MouseListener{
      override def mouseClicked(e: MouseEvent): Unit = {

         if(e.getButton == 1){
            waves += Wave(e.getX , e.getY , 250 , System.currentTimeMillis())
         }
         if(e.getButton == 2){
            moveMode = !moveMode
         }
         //右クリックしたときの関数
         if(e.getButton == 3){
            for(x <- 0 until getWidth by 75){
               for(y <- 0 until getHeight by 75){
                  waves += Wave(x , y , 225 , System.currentTimeMillis())
               }
            }
         }
      }
      override def mousePressed(e: MouseEvent): Unit = {}
      override def mouseReleased(e: MouseEvent): Unit = {}
      override def mouseEntered(e: MouseEvent): Unit = {}
      override def mouseExited(e: MouseEvent): Unit = {}
   }

   private case class KA ()extends KeyAdapter with KeyListener{
      override def keyTyped(e: KeyEvent): Unit = {
         if(e.getKeyCode == KeyEvent.VK_SPACE){
            println("Space")
            waves  += Wave(mx , my , 250 , System.currentTimeMillis())
         }
      }

      override def keyPressed(e: KeyEvent): Unit = super.keyPressed(e)

      override def keyReleased(e: KeyEvent): Unit = super.keyReleased(e)
   }

   override def keyTyped(e: KeyEvent): Unit = {
      if (e.getKeyCode == KeyEvent.VK_SPACE) {
         println("Space")
      }
   }
   override def keyPressed(e: KeyEvent): Unit = {}
   override def keyReleased(e: KeyEvent): Unit = {}
}
