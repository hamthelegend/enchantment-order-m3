package com.hamthelegend.enchantmentorder.android.ui.container

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.IconButton
import com.hamthelegend.enchantmentorder.composables.Updatable
import com.hamthelegend.enchantmentorder.composables.rememberMutableState

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigateUp: (() -> Unit)? = null,
    searchUpdatable: Updatable<String>? = null,
    scrolled: Boolean = false,
) {
    val scrollFraction by animateFloatAsState(
        targetValue = when (scrolled) {
            true -> 1f
            false -> 0f
        },
        animationSpec = tween(durationMillis = 50)
    )
    var searching by rememberMutableState(value = false)
    val color by smallTopAppBarColors().containerColor(scrollFraction = scrollFraction)

    Surface(
        color = color,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AnimatedVisibility(visible = !searching) {
                SmallTopAppBar(
                    title = { Text(title) },
                    navigationIcon = {
                        if (navigateUp != null) {
                            IconButton(
                                imageVector = ThemeIcons.ArrowBack,
                                contentDescription = stringResource(id = R.string.back),
                                onClick = navigateUp,
                            )
                        }
                    },
                    actions = {
                        if (searchUpdatable != null) {
                            IconButton(
                                imageVector = ThemeIcons.Search,
                                contentDescription = stringResource(id = R.string.search),
                                onClick = { searching = true }
                            )
                        }
                    },
                    colors = smallTopAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
            SearchBar(
                visible = searching,
                searchUpdatable = searchUpdatable ?: Updatable("") {},
                onStopSearching = { searching = false },
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun TopBarPreview() {
    EnchantmentOrderTheme {
        var searchQuery by rememberMutableState(value = "")
        var scrolled by rememberMutableState(value = false)
        TopBar(
            title = "Enchantment Order",
            navigateUp = { scrolled = !scrolled },
            searchUpdatable = Updatable(searchQuery) { searchQuery = it },
            scrolled = scrolled,
        )
    }
}