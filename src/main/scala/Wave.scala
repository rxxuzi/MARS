import java.awt.{Color, Graphics}

case class Wave(a:Int, b:Int, c:Int, time :Long) extends Core{
   W.x += a
   W.y += b
   final val maxRadius = 250

   private val colors = Array[Color](
         new Color(255, 0, 0),
      new Color(255, 125, 0),
      new Color(255, 255, 0),
      new Color(125, 255, 0),
      new Color(0, 255, 0),
      new Color(0, 255, 125),
      new Color(0, 255, 255),
      new Color(0, 125, 255),
      new Color(0, 0, 255),
      new Color(125, 0, 255),
      new Color(255, 0, 255),
      new Color(255, 0, 125),


   )

   private val speed = 30
//   println(time)
   def draw(g:Graphics): Unit = {

      val now = System.currentTimeMillis()
      val rad = ((now - time) / speed).toInt
      //how to use match

      rad % 12 match {
         case 0 => g.setColor(colors(0))
         case 1 => g.setColor(colors(1))
         case 2 => g.setColor(colors(2))
         case 3 => g.setColor(colors(3))
         case 4 => g.setColor(colors(4))
         case 5 => g.setColor(colors(5))
         case 6 => g.setColor(colors(6))
         case 7 => g.setColor(colors(7))
         case 8 => g.setColor(colors(8))
         case 9 => g.setColor(colors(9))
         case 10 => g.setColor(colors(10))
         case 11 => g.setColor(colors(11))
         case _ => g.setColor(colors(12))
      }
      if(rad < maxRadius){
         g.drawOval(W.x -rad/2, W.y-rad/2, rad, rad)
      }else{
         W.delete = true
      }
   }



   //波に関するobject
   object W{
      var x = 0
      var y = 0
      var delete = false
   }
}

