package com.koadr.services

import com.koadr.models.Item

trait Bundle {
  def eligibleForDiscount: Boolean = false
  def items: Seq[Item]
  def normalize(items: Seq[Item]): Seq[Item]
}

abstract class QuantityBundle(pairedPrice: Double) extends Bundle {
  def items: Seq[Item]

  override def eligibleForDiscount: Boolean = items.exists(_.quantity > 1)

  protected def normalizePrice(item: Item): Double = {
    if (eligibleForDiscount) {
      lazy val adjustedQuantity = item.quantity / 2
      lazy val adjustedCost = adjustedQuantity * pairedPrice
      lazy val discountedCost =
        if (item.quantity % 2 == 0) {
          adjustedCost
        } else {
          adjustedCost + item.price
        }
      discountedCost / item.quantity
    } else item.price
  }
}
