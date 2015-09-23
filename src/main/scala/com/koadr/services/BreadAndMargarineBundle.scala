package com.koadr.services

import com.koadr.models.Item
import com.koadr.models.Item.{Bread, Margarine}

case class BreadAndMargarineBundle(items: Seq[Item]) extends Bundle {
  override def eligibleForDiscount: Boolean =
    items.filter(_.isInstanceOf[Margarine]).exists(_.quantity > 1) &&
      items.exists(_.isInstanceOf[Bread])

  override def normalize(items: Seq[Item]): Seq[Item] = {
    val (breadAndMargarineItems, subItems) = items.partition(i => i.isInstanceOf[Bread] || i.isInstanceOf[Margarine])
    normalizeItem(breadAndMargarineItems) ++ subItems
  }

  private def normalizeItem(items: Seq[Item]): Seq[Item] = {
    if (eligibleForDiscount) {
      val Seq(bread,margarine) = items.toSeq
      val adjustedQuantity = margarine.quantity / 2
      val quantity =
        if (margarine.quantity % 2 == 0) {
          adjustedQuantity
        } else adjustedQuantity + 1
      val adjustedPrice = (margarine.price * quantity) / margarine.quantity
      Seq(Margarine(adjustedPrice,margarine.quantity),bread)
    } else items
  }
}

object BreadAndMargarineBundle {
  lazy val bundle: Seq[Item] => BreadAndMargarineBundle =
    items => BreadAndMargarineBundle(items)


}