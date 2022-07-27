package com.hamthelegend.enchantmentorder.domain.extensions

import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

fun enchantedBook(vararg enchantments: Enchantment) =
    Item(ItemType.EnchantedBook, enchantments.toList())