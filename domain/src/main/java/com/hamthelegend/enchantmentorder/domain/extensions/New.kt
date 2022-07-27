package com.hamthelegend.enchantmentorder.domain.extensions

import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

fun ItemType.toNewItem(vararg enchantments: Enchantment) =
    Item(type = this, enchantments = enchantments.toList(), anvilUseCount = 0)

fun new(itemType: ItemType, vararg enchantments: Enchantment) =
    itemType.toNewItem(*enchantments)