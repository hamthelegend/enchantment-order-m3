package com.hamthelegend.enchantmentorder.extensions

fun <T> Iterator<T>.nextOrNull() = if (hasNext()) next() else null