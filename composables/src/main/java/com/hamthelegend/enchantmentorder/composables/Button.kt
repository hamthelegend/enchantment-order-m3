package com.hamthelegend.enchantmentorder.composables

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

enum class IconPosition {
    Start,
    End,
}

@Composable
fun ElevatedButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    iconImageVector: ImageVector? = null,
    iconPosition: IconPosition = IconPosition.Start,
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
fun Button(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    iconImageVector: ImageVector? = null,
    iconPosition: IconPosition = IconPosition.Start,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
    ) {
        if (iconImageVector != null && iconPosition == IconPosition.Start) {
            Icon(imageVector = iconImageVector, contentDescription = text)
            HorizontalSpacer(width = 8.dp)
        }
        Text(text = text)
        if (iconImageVector != null && iconPosition == IconPosition.End) {
            HorizontalSpacer(width = 8.dp)
            Icon(imageVector = iconImageVector, contentDescription = text)
        }
    }
}

@Composable
fun FilledTonalButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    iconImageVector: ImageVector? = null,
    iconPosition: IconPosition = IconPosition.Start,
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        if (iconImageVector != null && iconPosition == IconPosition.Start) {
            Icon(imageVector = iconImageVector, contentDescription = text)
            HorizontalSpacer(width = 8.dp)
        }
        Text(text = text)
        if (iconImageVector != null && iconPosition == IconPosition.End) {
            HorizontalSpacer(width = 8.dp)
            Icon(imageVector = iconImageVector, contentDescription = text)
        }
    }
}

@Composable
fun OutlinedButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    iconImageVector: ImageVector? = null,
    iconPosition: IconPosition = IconPosition.Start,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        if (iconImageVector != null && iconPosition == IconPosition.Start) {
            Icon(imageVector = iconImageVector, contentDescription = text)
            HorizontalSpacer(width = 8.dp)
        }
        Text(text = text)
        if (iconImageVector != null && iconPosition == IconPosition.End) {
            HorizontalSpacer(width = 8.dp)
            Icon(imageVector = iconImageVector, contentDescription = text)
        }
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