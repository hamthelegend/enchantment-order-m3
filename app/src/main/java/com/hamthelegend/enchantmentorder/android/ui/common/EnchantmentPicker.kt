package com.hamthelegend.enchantmentorder.android.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.Modifier
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.itemsForEnchantmentPicker(
    enchantmentTypes: List<EnchantmentType>,
    selectedEnchantments: List<Enchantment>,
    selectEnchantment: (Enchantment) -> Unit,
    deselectEnchantment: (Enchantment) -> Unit,
) {
    itemsIndexed(
        items = enchantmentTypes,
        key = { _, enchantmentType ->
            enchantmentType.friendlyName
        },
    ) { index, enchantmentType ->
        val initialEnchantment =
            selectedEnchantments.firstOrNull { it.type == enchantmentType }
        val topActive = selectedEnchantments.any { enchantment ->
            enchantment.type == enchantmentTypes.getOrNull(index - 1)
        }
        val bottomActive = selectedEnchantments.any { enchantment ->
            enchantment.type == enchantmentTypes.getOrNull(index + 1)
        }

        EnchantmentLevelPicker(
            enchantmentType = enchantmentType,
            level = initialEnchantment?.level,
            select = { enchantment -> selectEnchantment(enchantment) },
            deselect = { enchantment -> deselectEnchantment(enchantment) },
            modifier = Modifier
                .fillMaxWidth()
                .animateItemPlacement(),
            topActive = topActive,
            bottomActive = bottomActive,
        )
    }
}