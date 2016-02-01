import ru.spbau.converters.currencyConverter._
import ru.spbau.converters.lengthConverter._
import ru.spbau.converters.date._
//import ru.spbau.converters.date.NoCheck._

object Converters {
  def main(args: Array[String]) {
    println(100.m to km)
    println(54.mi to yd)
    println(42)
//    new Day28(1).february(new Year(4))
//    new Day28(1).february(new LeapYear(4))
      println(29.february(2014))
      println(28.february(2013))
      println(28.february(2014))
//      println(29.february(2013))
    //println(30.january)
   // println(31.january)
  }
}
