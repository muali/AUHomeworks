package ru.spbau.converters

/**
 * Created by Moskvitin Maxim.
 */
package object lengthConverter {

  sealed trait Length {
    def meters : Double
  }

  object m extends Length {
    def meters = 1.0
  }

  object km extends Length {
    def meters = 1000.0
  }

  object mi extends Length {
    def meters = 1609.344
  }

  object ft extends Length {
    def meters = 0.3048
  }

  object yd extends Length {
    def meters = 0.9144
  }

  object in extends Length {
    def meters = 0.0254
  }

  case class Meters (value: Double) {
    def to(unit: Length) : Double = value / unit.meters
  }

  implicit class LengthProxy[T](value: T)(implicit n: Numeric[T]) {

    val units = n.toDouble(value)

    def m = Meters(units)
    def km = Meters(units * lengthConverter.km.meters)
    def mi = Meters(units * lengthConverter.mi.meters)
    def ft = Meters(units * lengthConverter.ft.meters)
    def yd = Meters(units * lengthConverter.yd.meters)
    def in = Meters(units * lengthConverter.in.meters)
  }
}
