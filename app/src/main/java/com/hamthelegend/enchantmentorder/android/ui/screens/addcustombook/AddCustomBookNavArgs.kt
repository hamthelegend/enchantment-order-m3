package com.hamthelegend.enchantmentorder.android.ui.screens.addcustombook

import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.extensions.decodeFromJsonString
import com.hamthelegend.enchantmentorder.extensions.encodeToJsonString
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer

@NavTypeSerializer
class ItemToBooleanNavTypeSerializer : DestinationsNavTypeSerializer<(Item) -> Boolean> {
    override fun toRouteString(value: (Item) -> Boolean): String =
        value.encodeToJsonString()

    override fun fromRouteString(routeStr: String): (Item) -> Boolean =
        routeStr.decodeFromJsonString()
}

data class AddCustomBookNavArgs(
    val edition: Edition,
    val target: Item,
    val isBookCompatible: (book: Item) -> Boolean,
    val addCustomBook: (book: Item) -> Boolean,
)
