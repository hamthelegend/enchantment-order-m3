package com.hamthelegend.enchantmentorder.composables

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Button(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    iconImageVector: ImageVector? = null,
) {
    Button(
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
fun ElevatedButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    iconImageVector: ImageVector? = null,
) {
    ElevatedButton(
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
fun TextButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(text = text)
    }
}