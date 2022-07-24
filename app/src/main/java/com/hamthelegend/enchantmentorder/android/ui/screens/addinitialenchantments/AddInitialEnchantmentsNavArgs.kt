package com.hamthelegend.enchantmentorder.android.ui.screens.addinitialenchantments

import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

data class AddInitialEnchantmentsNavArgs(
    val edition: Edition,
    val target: ItemType,
)
