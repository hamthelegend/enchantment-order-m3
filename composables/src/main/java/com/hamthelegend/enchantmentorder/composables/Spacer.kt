package com.hamthelegend.enchantmentorder.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Spacer(height: Dp = 0.dp, width: Dp = 0.dp) {
    Spacer(modifier = Modifier.height(height).width(width))
}