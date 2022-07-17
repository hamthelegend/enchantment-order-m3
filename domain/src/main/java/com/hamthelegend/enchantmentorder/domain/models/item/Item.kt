package com.hamthelegend.enchantmentorder.domain.models.item

import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Item(
    val type: ItemType,
    val enchantments: Set<Enchantment> = emptySet(),
    val anvilUseCount: Int = 0,
    val key: Double = Random.nextDouble(),
) {
    override fun toString() = "${type.friendlyName}: $enchantments"

    override fun equals(other: Any?) = if (other is Item) {
        type == other.type &&
                enchantments == other.enchantments &&
                anvilUseCount == other.anvilUseCount
    } else false

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + enchantments.hashCode()
        result = 31 * result + anvilUseCount
        return result
    }

}