package com.hamthelegend.enchantmentorder.android.ui.screens.choosebooks

import com.hamthelegend.enchantmentorder.domain.extensions.new
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

val defaultTarget = new(ItemType.Helmet)

data class ChooseBooksNavArgs(
    val edition: Edition = Edition.Java,
    val target: Item = defaultTarget,
)