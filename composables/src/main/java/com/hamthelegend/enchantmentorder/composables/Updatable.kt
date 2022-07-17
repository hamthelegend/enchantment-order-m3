package com.hamthelegend.enchantmentorder.composables

data class Updatable<T>(
    val value: T,
    val onValueChange: (newValue: T) -> Unit,
)
