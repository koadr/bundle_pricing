package com.koadr.services

import com.koadr.models.Item.Apple
import org.scalatest._


class AppleBundleTest extends FlatSpec with Matchers {
  it should "be eligible for discount if there are more than two items" in {
    val apple = Apple(1.99D, 3)
    val appleBundle = AppleBundle.bundle(Seq(apple))
    appleBundle.eligibleForDiscount should be (true)
  }

  it should "normalize price after discount" in {
    val apple = Apple(1.50, 3)
    // pairedPrice = $2.19
    // (1.99 + 2.19) / 3
    val appleBundle = AppleBundle.bundle(Seq(apple))
    val normalizedItem = appleBundle.normalize(Seq(apple)).headOption
    normalizedItem.isDefined should be (true)
    normalizedItem should equal(Some(Apple(1.23D,3)))
  }

  it should "not alter price if no discount" in {
    val apple = Apple(1.50, 1)
    // pairedPrice = $2.19
    // (1.99 + 2.19) / 3
    val appleBundle = AppleBundle.bundle(Seq(apple))
    val normalizedItem = appleBundle.normalize(Seq(apple)).headOption
    normalizedItem.isDefined should be (true)
    normalizedItem should equal(Some(apple))
  }


}
