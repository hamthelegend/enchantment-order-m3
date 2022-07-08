package com.hamthelegend.enchantmentorder.domain.exceptions

import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

open class CombinationException(message: String) :
    IllegalArgumentException(message)

class IncompatibleItemTypesException(
    targetItemType: ItemType,
    sacrificeItemType: ItemType,
) : CombinationException(
    "You cannot combine a/an ${targetItemType.friendlyName} with " +
            "a/an ${sacrificeItemType.friendlyName}."
)

class NoCompatibleEnchantmentsException(target: Item, sacrifice: Item) :
        CombinationException("There are no enchantments in $sacrifice that" +
                "are compatible with $target.")