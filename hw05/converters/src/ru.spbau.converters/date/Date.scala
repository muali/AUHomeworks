package ru.spbau.converters.date

import scala.language.experimental.macros
import scala.reflect.macros._


class Day31(day: Int) {
  def january = DayOfMonth(day, 1)
  def january(year: Year): Date = january of year

  def march = DayOfMonth(day, 3)
  def march(year: Year): Date = march of year

  def may = DayOfMonth(day, 5)
  def may(year: Year): Date = may of year

  def july = DayOfMonth(day, 7)
  def july(year: Year): Date = july of year

  def august = DayOfMonth(day, 8)
  def august(year: Year): Date = august of year

  def october = DayOfMonth(day, 10)
  def october(year: Year): Date = october of year

  def december = DayOfMonth(day, 12)
  def december(year: Year): Date = december of year
}

class Day30(day: Int) extends Day31(day) {
  def april = DayOfMonth(day, 4)
  def april(year: Year): Date = april of year

  def june = DayOfMonth(day, 6)
  def june(year: Year): Date = june of year

  def september = DayOfMonth(day, 9)
  def september(year: Year): Date = september of year

  def november = DayOfMonth(day, 11)
  def november(year: Year): Date = november of year
}

class Day29(day: Int) extends Day30(day) {
  def february(year: LeapYear) = new Date()
}

trait Day28Helper {
  def february(year: Year)
}

class Day28(day: Int) extends Day29(day) with Day28Helper{
  override def february(year: Year) = new Date()
}

case class DayOfMonth(day: Int, month: Int) {
  def of(year: Year): Date = new Date()
}

case class DayOfMonthLeap(day: Int, month: Int) {
  def of(year: LeapYear): Date = new Date()
}

class LeapYear(year: Int) {
}

class Year(year: Int) {
}



class Date {

}
