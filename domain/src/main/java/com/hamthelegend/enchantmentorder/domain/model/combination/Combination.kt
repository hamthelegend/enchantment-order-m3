package com.hamthelegend.enchantmentorder.domain.model.combination

import com.hamthelegend.enchantmentorder.domain.model.item.Item

data class Combination(
    val target: Item,
    val sacrifice: Item,
    val product: Item,
    val cost: Int,
) {
    override fun toString() = "$target + $sacrifice = $product; Cost = $cost"
}