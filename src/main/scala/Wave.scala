import java.awt.{Color, Graphics}
import scala.math.random
import scala.util.Random

case class Wave(a:Int, b:Int, c:Int, time :Long) extends Core{
   val rand = new Random()
   W.x += a
   W.y += b
   private final val maxRadius = c
   private final val drawCircle = true

   private abstract class Move(){
      def move(g:Graphics): Unit = {
         if(W.x < 0) F.moveToR = true
         if(W.x > getWidth) F.moveToR = false
         if (F.moveToR) {
            W.x += 1
         }
         if (F.moveToD) {
            W.y += 1
         }
      }
      object F{
         var moveToR = false
         var moveToD = false
      }
   }
   private final val colors = Array[Color](
         new Color(255, 0, 0),
      new Color(255, 85, 0),
      new Color(255, 170, 0),
      new Color(255, 255, 0),
      new Color(170, 255, 0),
      new Color(85, 255, 0),
      new Color(0, 255, 0),
      new Color(0, 255, 85),
      new Color(0, 255, 170),
      new Color(0, 255, 255),
      new Color(0, 170, 255),
      new Color(0, 85, 255),
      new Color(0, 0, 255),
      new Color(85, 0, 255),
      new Color(170, 0, 255),
      new Color(255, 0, 255),
      new Color(255, 0, 170),
      new Color(255, 0, 85),
   )

   private val speed = 30

   //   println(time)
   def draw(g:Graphics): Unit = {

      val now = System.currentTimeMillis()
      val rad = ((now - time) / speed).toInt
      val dt = rad / 50d
      g.setColor(colors(rad % colors.length))

      if(drawCircle){
         g.drawOval(W.x -rad, W.y-rad, rad*2, rad*2)
      }else{
         val corner = 5
         val dx = new Array[Int](corner)
         val dy = new Array[Int](corner)

         for (i <- 0 until corner){
            dx(i) = (W.x + rad * math.cos(i * 2 * math.Pi / corner - dt)).toInt
            dy(i) = (W.y + rad * math.sin(i * 2 * math.Pi / corner - dt)).toInt
         }
         for (i <- 0 until 5){
            if(i < corner - 2){
               g.drawLine(dx(i), dy(i), dx(i+2), dy(i+2))
            }else if (i == corner -2){
               g.drawLine(dx(i), dy(i), dx(0), dy(0))
            }else if (i == corner -1){
               g.drawLine(dx(i), dy(i), dx(1), dy(1))
            }
         }
         g.drawOval(W.x -rad, W.y-rad, rad*2, rad*2)
      }



      if(rad > maxRadius){
         W.delete = true
      }

      //abstract class
      new Move(){
         override def move(g:Graphics): Unit = {
            super.move(g)
            g.drawOval(F.x,F.y,rad,rad)
         }
      }


   }

   //波に関するobject
   object W{
      var x = 0
      var y = 0
      var delete = false
   }
}

