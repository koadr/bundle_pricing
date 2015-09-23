package com.koadr.services

import com.koadr.models.Item.{Margarine, Bread, Apple}
import org.scalatest.{Matchers, FlatSpec}

class BreadAndMargarineBundleTest extends FlatSpec with Matchers {
  it should "be eligible for discount if there are more than two margarine items as well as a bread item" in {
    val bread = Bread(1.50,3)
    val margarine = Margarine(1.30,2)
    val breadAndMargerineBundle = BreadAndMargarineBundle.bundle(Seq(bread,margarine))
    breadAndMargerineBundle.eligibleForDiscount should be (true)
  }

  it should "normalize price after discount" in {
    val bread = Bread(1.50,3)
    val margarine = Margarine(1.50,2)
    val breadAndMargerineBundle = BreadAndMargarineBundle.bundle(Seq(bread,margarine))
    val normalizedItem = breadAndMargerineBundle.normalize(Seq(bread,margarine))
    normalizedItem should have length (2)
    val normalizedMargarine = normalizedItem.find(_.isInstanceOf[Margarine])
    normalizedMargarine should equal(Some(Margarine(0.75D,2)))
  }

  it should "not alter price if no discount" in {
    val margarine = Margarine(1.50,2)
    val breadAndMargerineBundle = BreadAndMargarineBundle.bundle(Seq(margarine))
    val normalizedItem = breadAndMargerineBundle.normalize(Seq(margarine))
    val normalizedMargarine = normalizedItem.find(_.isInstanceOf[Margarine])
    normalizedMargarine should equal(Some(margarine))
  }

}
