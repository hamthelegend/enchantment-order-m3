package com.hamthelegend.enchantmentorder.domain.extensions

import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType

fun EnchantmentType.toMaxEnchantment() = Enchantment(this, maxLevel)

fun max(enchantmentType: EnchantmentType) = enchantmentType.toMaxEnchantment()