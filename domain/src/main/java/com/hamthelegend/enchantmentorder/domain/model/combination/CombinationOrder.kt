package com.hamthelegend.enchantmentorder.domain.model.combination

data class CombinationOrder(
    val combinations: List<Combination>,
    val name: String = combinations.last().target.type.friendlyName,
    val id: Int = -1,
) {
    val totalCost = combinations.sumOf { it.cost }
    val finalProduct = combinations.last().product
}