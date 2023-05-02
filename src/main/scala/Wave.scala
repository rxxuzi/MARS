import java.awt.{Color, Graphics}

case class Wave(a:Int, b:Int, c:Int, time :Long) extends Core{
   W.x += a
   W.y += b
   final val maxRadius = 250

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

   def setColor(g: Graphics, rad: Int) = {
      rad % colors.length match {
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
         case 12 => g.setColor(colors(12))
         case 13 => g.setColor(colors(13))
         case 14 => g.setColor(colors(14))
         case 15 => g.setColor(colors(15))
         case 16 => g.setColor(colors(16))
         case 17 => g.setColor(colors(17))
         case 18 => g.setColor(colors(18))
         case _ => g.setColor(Color.white)

      }
   }

   //   println(time)
   def draw(g:Graphics): Unit = {

      val now = System.currentTimeMillis()
      val rad = ((now - time) / speed).toInt
      //how to use match
      setColor(g ,rad)
      val corner = 5
      val dx = new Array[Int](5)
      val dy = new Array[Int](5)
      for (i <- 0 until 5){
         dx(i) = (W.x + rad * math.cos(i * 2 * math.Pi / 5)).toInt
         dy(i) = (W.y + rad * math.sin(i * 2 * math.Pi / 5)).toInt
      }
      g.drawPolygon(dx,dy,5)
      //2shift  dx(i)
      //2shift  dy(i)
//      for (i <- 0 until 5){
//         if(i < 3){
//            g.drawLine(dx(i), dy(i), dx(i+2), dy(i+2))
//         }else if (i == 3){
//            g.drawLine(dx(i), dy(i), dx(0), dy(0))
//         }else if (i == 4){
//            g.drawLine(dx(i), dy(i), dx(1), dy(1))
//         }
//      }

      if(rad < maxRadius){
//         g.drawPolygon(x,y,5)
//         g.drawOval(W.x -rad/2, W.y-rad/2, rad, rad)
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

