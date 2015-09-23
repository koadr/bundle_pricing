package com.koadr.services

import com.koadr.models.Item.{Mango, Margarine, Bread, Apple}
import org.scalatest.{Matchers, FlatSpec}

class PricingServiceTest extends FlatSpec with Matchers {
  it should "calculate lowest price to be the same if no discounts apply" in {
    val bread = Bread(1.50D,1)
    val margarine = Margarine(1.00D,1)
    val apple = Apple(2.00D, 1)
    val mango = Mango(3.50D,1)
    val shoppingItems = Seq(apple,bread,mango,margarine)

    // Bundles
    val appleBundle = AppleBundle.bundle
    val mangoBundle = MangoBundle.bundle
    val breadAndMargarineBundle = BreadAndMargarineBundle.bundle
    val breadBundle = BreadBundle.bundle
    val bundles = Seq(appleBundle, breadBundle, breadAndMargarineBundle, mangoBundle)
    val pricingService = new PricingServiceImpl(bundles) {}
    pricingService.calculateLowestPrice(shoppingItems) should equal(8.00D)

  }

  it should "calculate lowest price when singular discounts apply" in {
    val bread = Bread(1.50D,1)
    val margarine = Margarine(1.00D,2)
    val apple = Apple(2.00D, 1)
    val mango = Mango(3.50D,1)
    val shoppingItems = Seq(apple,bread,mango,margarine)

    // Bundles
    val appleBundle = AppleBundle.bundle
    val mangoBundle = MangoBundle.bundle
    val breadAndMargarineBundle = BreadAndMargarineBundle.bundle
    val breadBundle = BreadBundle.bundle
    val bundles = Seq(appleBundle, breadBundle, breadAndMargarineBundle, mangoBundle)
    val pricingService = new PricingServiceImpl(bundles) {}
    pricingService.calculateLowestPrice(shoppingItems) should equal(8.00D)

  }

  it should "calculate lowest price for a combination of discounts" in {
    val bread = Bread(1.50D,3) // 1.50 + 2.15 => 3.65
    val margarine = Margarine(1.00D,2) // 1
    val apple = Apple(2.00D, 2) // 2.15
    val mango = Mango(3.50D,1) // 3.50
    // Total => 3.65 + 1 + 2.15 + 3.50 =
    val shoppingItems = Seq(apple,bread,mango,margarine)

    // Bundles
    val appleBundle = AppleBundle.bundle
    val mangoBundle = MangoBundle.bundle
    val breadAndMargarineBundle = BreadAndMargarineBundle.bundle
    val breadBundle = BreadBundle.bundle
    val bundles = Seq(appleBundle, breadBundle, breadAndMargarineBundle, mangoBundle)
    val pricingService = new PricingServiceImpl(bundles) {}
    pricingService.calculateLowestPrice(shoppingItems) should equal(10.34D)

  }

}
