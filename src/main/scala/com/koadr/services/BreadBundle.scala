package com.koadr.services

import com.koadr.models.Item
import com.koadr.models.Item.Bread

case class BreadBundle(items: Seq[Item]) extends QuantityBundle(2.15) {

  override def normalize(items: Seq[Item]): Seq[Item] = {
    items map {
      case i: Bread =>
        val priceAfterDiscount = normalizePrice(i)
        Bread(priceAfterDiscount,i.quantity)
      case i => i
    }
  }
}

object BreadBundle {
  lazy val bundle: Seq[Item] => BreadBundle =
    items => BreadBundle(items)
}