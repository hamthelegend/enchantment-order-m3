package com.hamthelegend.enchantmentorder.domain.model.combination

import com.hamthelegend.enchantmentorder.domain.model.item.Item
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