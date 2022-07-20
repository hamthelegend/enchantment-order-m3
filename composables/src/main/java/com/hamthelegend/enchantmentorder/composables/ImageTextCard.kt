package com.hamthelegend.enchantmentorder.composables

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageTextCard(
    @DrawableRes painterResourceId: Int,
    text: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)?,
    active: Boolean = false,
    topActive: Boolean = false,
    bottomActive: Boolean = false,
) {
    val topCornerRadius by animateDpAsState(
        targetValue = when {
            active && topActive -> 0.dp
            active -> 8.dp
            else -> 0.dp
        }
    )
    val bottomCornerRadius by animateDpAsState(
        targetValue = when {
            active && bottomActive -> 0.dp
            active -> 8.dp
            else -> 0.dp
        }
    )
    val containerColor by animateColorAsState(
        targetValue = when {
            active -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.background
        }
    )
    val contentColor by animateColorAsState(
        targetValue = when {
            active -> MaterialTheme.colorScheme.onPrimaryContainer
            else -> MaterialTheme.colorScheme.onBackground
        }
    )

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        shape = MaterialTheme.shapes.medium.copy(
            topStart = CornerSize(topCornerRadius),
            topEnd = CornerSize(topCornerRadius),
            bottomStart = CornerSize(bottomCornerRadius),
            bottomEnd = CornerSize(bottomCornerRadius),
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .run { if (onClick != null) clickable { onClick() } else this }
                .padding(16.dp),
        ) {
            Image(
                painter = painterResource(id = painterResourceId),
                contentDescription = text,
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageTextCard(
    imageVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)?,
    active: Boolean = false,
    topActive: Boolean = false,
    bottomActive: Boolean = false,
) {
    val topCornerRadius by animateDpAsState(
        targetValue = when {
            active && topActive -> 0.dp
            active -> 8.dp
            else -> 0.dp
        }
    )
    val bottomCornerRadius by animateDpAsState(
        targetValue = when {
            active && bottomActive -> 0.dp
            active -> 8.dp
            else -> 0.dp
        }
    )
    val containerColor by animateColorAsState(
        targetValue = when {
            active -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.background
        }
    )
    val contentColor by animateColorAsState(
        targetValue = when {
            active -> MaterialTheme.colorScheme.onPrimaryContainer
            else -> MaterialTheme.colorScheme.onBackground
        }
    )

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        shape = MaterialTheme.shapes.medium.copy(
            topStart = CornerSize(topCornerRadius),
            topEnd = CornerSize(topCornerRadius),
            bottomStart = CornerSize(bottomCornerRadius),
            bottomEnd = CornerSize(bottomCornerRadius),
        ),
        onClick = { if (onClick != null) { onClick() } },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp),
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = text,
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun ImageTextCardPreview() {
    ImageTextCard(
        imageVector = Icons.Default.AccountCircle,
        text = "Trident",
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    )
}

@Preview
@Composable
fun ImageTextCardListPreview() {
    val active = remember { mutableStateListOf(false, false, false, false, false) }

    LazyColumn {
        itemsIndexed(active) { index, currentActive ->
            val beforeActive = active.getOrElse(index - 1) { false }
            val afterActive = active.getOrElse(index + 1) { false }

            ImageTextCard(
                imageVector = Icons.Default.AccountCircle,
                text = "Trident",
                onClick = { active[index] = !currentActive },
                modifier = Modifier
                    .fillMaxWidth(),
                active = currentActive,
                topActive = beforeActive,
                bottomActive = afterActive,
            )
        }
    }

}