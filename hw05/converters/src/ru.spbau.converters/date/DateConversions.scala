package ru.spbau.converters.date

import scala.language.experimental.macros
import scala.reflect.macros.whitebox


//class DayConversionsMacros(val c: Context) {
//
//  import c.universe._
//
//  def int2DayImpl[T : TypeTag](value: c.Expr[Int], limit: Int) : c.Expr[T] = {
//    value.tree match {
//      case Literal(Constant(x: Int)) if x > 0 && x <= limit => c.Expr[T](q"new ${c.typeOf[T]}($value)")
//      case Literal(Constant(x: Int)) => c.abort(c.enclosingPosition, s"Invalid day, found: $x, upper limit: $limit")
//      case _ => c.Expr[T](q"new ${c.typeOf[T]}($value)")
//    }
//  }
//
//  def int2Day31Impl(value: c.Expr[Int]) : c.Expr[Day31] = int2DayImpl[Day31](value, 31)
//  def int2Day30Impl(value: c.Expr[Int]) : c.Expr[Day30] = int2DayImpl[Day30](value, 30)
//  def int2Day29Impl(value: c.Expr[Int]) : c.Expr[Day29] = int2DayImpl[Day29](value, 29)
//}

object DateConversionsMacros {
  def int2DayImpl[T](c: whitebox.Context)(day: c.Expr[Int]): c.Expr[T] = {
    import c.universe._
    day.tree match {
      case Literal(Constant(value: Int)) => {
        value match {
          case 31 => c.Expr[T](q"new Day31($value)")
          case 30 => c.Expr[T](q"new Day30($value)")
          case 29 => c.Expr[T](q"new Day29($value)")
          case x if x > 0 && x <= 28 => c.Expr[T](q"new Day28($value)")
          case _ => c.abort(c.enclosingPosition, s"Invalid day: $value")
        }
      }
      case _ => c.Expr[T](q"new Day28($day)")
    }
  }

  def int2YearImpl[T: c.WeakTypeTag](c: whitebox.Context)(year: c.Expr[Int]): c.Expr[T] = {
    import c.universe._
    val tpe = implicitly[c.WeakTypeTag[T]].tpe
    year.tree match {
      case Literal(Constant(value: Int)) if value == 2014 && tpe =:= typeOf[LeapYear] => c.Expr[T](q"new LeapYear(2014)")
      case Literal(Constant(value: Int)) => c.Expr[T](q"new Year($value)")
    }
  }

}


class FebruaryDateConversion {
    //implicit def int2FebruaryDay[T >: FebruaryDay](day: Int): T = macro DateConversionsMacros.int2DayImpl
  implicit def int2LeapYear(year: Int): LeapYear = macro DateConversionsMacros.int2YearImpl[LeapYear]
}



class DateConversions extends FebruaryDateConversion {
  implicit def int2Day[T >: Day29](day: Int): T = macro DateConversionsMacros.int2DayImpl[T]

  implicit def int2Year(year: Int): Year = macro DateConversionsMacros.int2YearImpl[Year]

//  implicit def int2Day31(day: Int): Day31 = macro DateConversionsMacros.int2DayImpl[Day31]
//  implicit def int2Day28(day: Int): Day28 = macro DateConversionsMacros.int2Day28Impl

//  implicit def int2Day[T >: Day31](day: Int): T = macro DateConversionsMacros.int2DayImpl
//  implicit def int2Day29[T >: Day29Conv](day: Int): T = macro DateConversionsMacros.int2DayImpl[T]
}