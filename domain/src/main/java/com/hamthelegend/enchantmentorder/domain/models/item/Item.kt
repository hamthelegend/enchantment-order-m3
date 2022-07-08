package com.hamthelegend.enchantmentorder.domain.models.item

import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import kotlin.random.Random

data class Item(
    val type: ItemType,
    val enchantments: Set<Enchantment> = emptySet(),
    val anvilUseCount: Int = 0,
    val key: Double = Random.nextDouble(),
) {
    override fun toString() = "${type.friendlyName}: $enchantments"
}