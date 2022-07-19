package com.hamthelegend.enchantmentorder.composables

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ExtendedFloatingActionButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    iconImageVector: ImageVector? = null,
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        if (iconImageVector != null) {
            Icon(imageVector = iconImageVector, contentDescription = text)
        }
        Text(text = text)
    }
}

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