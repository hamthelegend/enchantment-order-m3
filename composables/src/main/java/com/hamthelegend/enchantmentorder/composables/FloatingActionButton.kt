package com.hamthelegend.enchantmentorder.composables

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FloatingActionButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String?,
) {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}