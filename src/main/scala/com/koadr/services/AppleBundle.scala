package com.koadr.services

import com.koadr.models.Item
import com.koadr.models.Item.Apple


case class AppleBundle(items: Seq[Item]) extends QuantityBundle(2.19) {
  override def normalize(items: Seq[Item]): Seq[Item] = {
    items map {
      case i: Apple =>
        val priceAfterDiscount = normalizePrice(i)
        Apple(priceAfterDiscount,i.quantity)
      case i => i
    }
  }
}

object AppleBundle {
  lazy val bundle: Seq[Item] => AppleBundle =
    items => AppleBundle(items)
}

