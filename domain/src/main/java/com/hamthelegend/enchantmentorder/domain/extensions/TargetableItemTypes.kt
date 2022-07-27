package com.hamthelegend.enchantmentorder.domain.extensions

import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

val targetableItemTypes = ItemType.values().toList() - ItemType.EnchantedBook