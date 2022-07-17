package com.hamthelegend.enchantmentorder.extensions

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> T.encodeToJsonString() =
    Json.encodeToString(this)

inline fun <reified T> String.decodeFromJsonString() =
    Json.decodeFromString<T>(this)