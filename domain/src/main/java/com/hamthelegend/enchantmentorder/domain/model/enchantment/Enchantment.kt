package com.hamthelegend.enchantmentorder.domain.model.enchantment

import com.hamthelegend.enchantmentorder.extensions.toRomanNumerals
import kotlin.random.Random

data class Enchantment(
    val type: EnchantmentType,
    val level: Int = 1,
    val key: Double = Random.nextDouble(),
) {
    override fun toString() = "${type.friendlyName} ${level.toRomanNumerals()}"
    val abbreviatedString = "${type.abbreviatedName} $level"
    val arabicLevelString = "${type.friendlyName} $level"
    fun upgradeBy(level: Int) = upgradeTo(this.level + level)
    fun upgradeTo(level: Int) = Enchantment(type, level)
}

fun EnchantmentType.toMaxEnchantment() = Enchantment(this, maxLevel)

fun max(enchantmentType: EnchantmentType) = enchantmentType.toMaxEnchantment()