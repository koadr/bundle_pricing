package com.koadr.services

import com.koadr.models.Item
import com.koadr.models.Item.Mango


case class MangoBundle(items: Seq[Item]) extends QuantityBundle(4.15) {

  override def normalize(items: Seq[Item]): Seq[Item] = {
    items map {
      case i: Mango =>
        val priceAfterDiscount = normalizePrice(i)
        Mango(priceAfterDiscount,i.quantity)
      case i => i
    }
  }
}

object MangoBundle {
  lazy val bundle: Seq[Item] => MangoBundle =
    items => MangoBundle(items)
}