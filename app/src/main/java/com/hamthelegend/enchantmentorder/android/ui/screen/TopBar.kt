package com.hamthelegend.enchantmentorder.android.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.hamthelegend.enchantmentorder.composables.rememberMutableStateOf

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigateUp: (() -> Unit)? = null,
    searchQuery: String? = null,
    onSearchQueryChange: (newQuery: String) -> Unit = {},
    otherActions: @Composable RowScope.() -> Unit = {},
    scrolled: Boolean = false,
) {
    val scrollFraction by animateFloatAsState(
        targetValue = when (scrolled) {
            true -> 1f
            false -> 0f
        },
        animationSpec = tween(durationMillis = 50)
    )
    var searching by rememberMutableStateOf(value = false)
    val color by smallTopAppBarColors().containerColor(scrollFraction = scrollFraction)

    Surface(
        color = color,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.statusBarsPadding(),
        ) {
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
                        if (searchQuery != null) {
                            IconButton(
                                imageVector = ThemeIcons.Search,
                                contentDescription = stringResource(id = R.string.search),
                                onClick = { searching = true }
                            )
                        }
                        otherActions()
                    },
                    colors = smallTopAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
            AnimatedVisibility(
                visible = searching,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                SearchBar(
                    shouldBeInFocus = searching,
                    query = searchQuery ?: "",
                    onQueryChange = onSearchQueryChange,
                    onStopSearching = {
                        searching = false
                    },
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun TopBarPreview() {
    EnchantmentOrderTheme {
        var searchQuery by rememberMutableStateOf(value = "")
        var scrolled by rememberMutableStateOf(value = false)
        TopBar(
            title = "Enchantment Order",
            navigateUp = { scrolled = !scrolled },
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            scrolled = scrolled,
        )
    }
}