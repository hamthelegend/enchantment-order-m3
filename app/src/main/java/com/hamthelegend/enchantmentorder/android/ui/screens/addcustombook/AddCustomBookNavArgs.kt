package com.hamthelegend.enchantmentorder.android.ui.screens.addcustombook

import androidx.compose.ui.input.key.Key.Companion.G
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hamthelegend.enchantmentorder.domain.models.combination.Combination
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.extensions.decodeFromJsonString
import com.hamthelegend.enchantmentorder.extensions.encodeToJsonString
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer
import java.lang.reflect.Type
import kotlin.reflect.KFunction
import kotlin.reflect.KType

data class AddCustomBookNavArgs(
    val edition: Edition,
    val target: Item,
)