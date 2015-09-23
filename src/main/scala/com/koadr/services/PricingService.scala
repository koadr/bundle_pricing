package com.koadr.services

import com.koadr.models.Item


trait PricingService {
  def calculateLowestPrice(items: Seq[Item]): Double
  protected def calcPrice(items: Seq[Item]) = {
    items.map(i => i.price * i.quantity).sum
  }
}

abstract class PricingServiceImpl(
  bundlesFn: Seq[Seq[Item] => Bundle]) extends PricingService {



  private def applyBundles(bundles: Seq[Bundle], items: Seq[Item]) = {
    bundles.foldLeft(items) {
      case (acc, b) => b.normalize(acc)
    }
  }

  override def calculateLowestPrice(items: Seq[Item]): Double = {
    // Look at permutations of combining bundles and select lowest one
    val bundlesPermutations: Seq[Seq[Bundle]] = bundlesFn.map(_(items)).permutations.toSeq
    val prices =
      (for {
        bundles <- bundlesPermutations
       } yield applyBundles(bundles, items)
      ) map calcPrice
    roundPrice(prices.min)
  }

  private def roundPrice(price: Double): Double = Math.round(price * 100.0)/100.0

}
