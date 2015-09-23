package com.koadr.models


sealed trait Item {
  def price: Double
  def quantity: Int
  def category: String = "General"
}


object Item {
  case class Bread(price: Double, quantity: Int) extends Item
  case class Margarine(price: Double, quantity: Int) extends Item
  case class Apple(price: Double, quantity: Int) extends Item
  case class Mango(price: Double, quantity: Int) extends Item
}