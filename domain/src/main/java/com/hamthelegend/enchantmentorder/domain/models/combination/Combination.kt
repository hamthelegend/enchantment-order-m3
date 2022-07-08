package com.hamthelegend.enchantmentorder.domain.models.combination

import com.hamthelegend.enchantmentorder.domain.models.item.Item
import kotlin.random.Random

data class Combination(
    val target: Item,
    val sacrifice: Item,
    val product: Item,
    val cost: Int,
    val key: Double = Random.nextDouble(),
) {
    override fun toString() = "$target + $sacrifice = $product; Cost = $cost"
}