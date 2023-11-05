package com.hamthelegend.enchantmentorder.composables

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ExtendedFloatingActionButton(
    onClick: () -> Unit,
    iconImageVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor: Color = contentColorFor(containerColor),
) {
    ExtendedFloatingActionButton(
        icon = { Icon(imageVector = iconImageVector, contentDescription = text) },
        text = { Text(text = text) },
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        expanded = expanded,
    )
}

@Composable
fun FloatingActionButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor: Color = contentColorFor(containerColor),
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}