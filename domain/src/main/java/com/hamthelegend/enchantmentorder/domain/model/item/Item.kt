package com.hamthelegend.enchantmentorder.domain.model.item

import com.hamthelegend.enchantmentorder.domain.model.enchantment.Enchantment
import kotlin.random.Random

data class Item(
    val type: ItemType,
    val enchantments: Set<Enchantment> = emptySet(),
    val anvilUseCount: Int = 0,
    val key: Double = Random.nextDouble(),
) {
    override fun toString() = "${type.friendlyName}: $enchantments"
}

fun ItemType.toNewItem(vararg enchantments: Enchantment) =
    Item(type = this, enchantments = enchantments.toSet(), anvilUseCount = 0)

fun new(itemType: ItemType, vararg enchantments: Enchantment) =
    itemType.toNewItem(*enchantments)