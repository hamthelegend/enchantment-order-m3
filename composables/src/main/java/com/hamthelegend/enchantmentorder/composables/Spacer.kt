package com.hamthelegend.enchantmentorder.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpacer(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun VerticalSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun Spacer(size: Dp) {
    Spacer(modifier = Modifier.size(size))
}