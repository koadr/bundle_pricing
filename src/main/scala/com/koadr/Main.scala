package com.koadr

import com.koadr.models.Item._
import com.koadr.services._

object Main extends App {
  // From shopping user (API)
  val bread = Bread(1.50D,3)
  val margarine = Margarine(0.92D,4)
  val apple = Apple(1.99D, 3)
  val mango = Mango(3.50D,2)
  val shoppingItems = Seq(apple,bread,mango,margarine)

  // Bundles
  val appleBundle = AppleBundle.bundle
  val mangoBundle = MangoBundle.bundle
  val breadAndMargarineBundle = BreadAndMargarineBundle.bundle
  val breadBundle = BreadBundle.bundle
  val bundles = Seq(appleBundle, breadBundle, breadAndMargarineBundle, mangoBundle)
  val pricingService = new PricingServiceImpl(bundles) {}
  pricingService.calculateLowestPrice(shoppingItems)

}
