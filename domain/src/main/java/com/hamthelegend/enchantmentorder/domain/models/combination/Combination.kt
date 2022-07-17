package com.hamthelegend.enchantmentorder.domain.models.combination

import com.hamthelegend.enchantmentorder.domain.models.item.Item
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Combination(
    val target: Item,
    val sacrifice: Item,
    val product: Item,
    val cost: Int,
    val key: Double = Random.nextDouble(),
) {

    override fun toString() = "$target + $sacrifice = $product; Cost = $cost"

    override fun equals(other: Any?) = if (other is Combination) {
        target == other.target &&
                sacrifice == other.sacrifice &&
                product == other.product &&
                cost == other.cost
    } else false

    override fun hashCode(): Int {
        var result = target.hashCode()
        result = 31 * result + sacrifice.hashCode()
        result = 31 * result + product.hashCode()
        result = 31 * result + cost
        return result
    }

}