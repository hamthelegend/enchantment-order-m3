package com.hamthelegend.enchantmentorder.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun <T> rememberMutableStateOf(value: T) =
    remember { mutableStateOf(value) }

@Composable
fun <T> rememberDerivedStateOf(value: () -> T) =
    remember { derivedStateOf(value) }