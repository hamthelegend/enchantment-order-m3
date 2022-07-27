package com.hamthelegend.enchantmentorder.domain.extensions

import kotlin.math.ln
import kotlin.math.pow

fun Int.anvilUseCountToCost() = (2.0.pow(this) - 1).toInt()

fun Int.costToAnvilUseCount() = (ln(this.toDouble() + 1) / ln(2.0)).toInt()

fun Int.renamingCostToAnvilUseCount() = (this - 1).costToAnvilUseCount()