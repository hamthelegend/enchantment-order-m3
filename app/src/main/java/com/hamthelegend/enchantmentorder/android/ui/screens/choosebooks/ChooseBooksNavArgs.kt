package com.hamthelegend.enchantmentorder.android.ui.screens.choosebooks

import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

data class ChooseBooksNavArgs(
    val edition: Edition,
    val target: Item,
)