package com.hamthelegend.enchantmentorder.domain.businesslogic

import com.hamthelegend.enchantmentorder.domain.models.combination.Combination
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import kotlin.math.max

fun combine(
    target: Item,
    sacrifice: Item,
    edition: Edition,
): Combination {
    sacrifice checkIsCompatibleWith target
    var combiningCost = target.anvilUseCount.anvilUseCountToCost() +
            sacrifice.anvilUseCount.anvilUseCountToCost()
    val productEnchantments = target.enchantments.toMutableList()
    val targetEnchantmentTypes = target.enchantments.map { it.type }
    for (sacrificeEnchantment in sacrifice.enchantments) {
        val sacrificeEnchantmentIndexInTarget =
            targetEnchantmentTypes.indexOf(sacrificeEnchantment.type)
        val sacrificeEnchantmentNotInTarget = sacrificeEnchantmentIndexInTarget == -1
        val costMultiplier = sacrificeEnchantment.type.multiplierFor(sacrifice.type, edition)
        if (sacrificeEnchantment.type isIncompatibleWith target.type) {
            continue
        }
        if (sacrificeEnchantment.type isIncompatibleWith targetEnchantmentTypes) {
            if (edition == Edition.Java) combiningCost += 1
            continue
        }
        if (sacrificeEnchantmentNotInTarget) {
            productEnchantments.add(sacrificeEnchantment)
            combiningCost += sacrificeEnchantment.level * costMultiplier
            continue
        }
        val targetEnchantment = target.enchantments.toList()[sacrificeEnchantmentIndexInTarget]
        when {
            sacrificeEnchantment.level > targetEnchantment.level -> {
                productEnchantments[sacrificeEnchantmentIndexInTarget] = sacrificeEnchantment
                combiningCost += when (edition) {
                    Edition.Bedrock -> (sacrificeEnchantment.level - targetEnchantment.level) * costMultiplier
                    Edition.Java -> sacrificeEnchantment.level * costMultiplier
                }
            }
            sacrificeEnchantment.level == targetEnchantment.level &&
                    targetEnchantment.level < targetEnchantment.type.maxLevel -> {
                val upgradedEnchantment = targetEnchantment.upgradeBy(1)
                productEnchantments[sacrificeEnchantmentIndexInTarget] = upgradedEnchantment
                combiningCost += when (edition) {
                    Edition.Bedrock -> costMultiplier
                    Edition.Java -> upgradedEnchantment.level * costMultiplier
                }
            }
            else -> combiningCost += when (edition) {
                Edition.Bedrock -> 0
                Edition.Java -> targetEnchantment.level * costMultiplier
            }
        }
    }
    productEnchantments.sortBy { it.type.friendlyName }
    val productAnvilUseCount = max(target.anvilUseCount, sacrifice.anvilUseCount) + 1
    val productItem = Item(target.type, productEnchantments.toList(), productAnvilUseCount)
    return Combination(target, sacrifice, productItem, combiningCost)
}