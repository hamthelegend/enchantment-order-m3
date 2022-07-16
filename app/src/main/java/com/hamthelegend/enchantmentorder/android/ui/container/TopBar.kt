package com.hamthelegend.enchantmentorder.android.ui.container

import androidx.compose.animation.AnimatedVisibility
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
import com.hamthelegend.enchantmentorder.composables.rememberMutableState

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigateUp: (() -> Unit)? = null,
    searchQuery: String = "",
    onSearchQueryChange: ((newQuery: String) -> Unit)? = null,
    scrollFraction: Float = 0f,
) {
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
                        if (onSearchQueryChange != null) {
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
                visible = onSearchQueryChange != null,
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange!!,
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
        var scrollFraction by rememberMutableState(value = 0f)
        TopBar(
            title = "Enchantment Order",
            navigateUp = {
                scrollFraction = if (scrollFraction == 0f) 1f else 0f
            },
            searchQuery = searchQuery,
            onSearchQueryChange = { newQuery -> searchQuery = newQuery },
            scrollFraction = scrollFraction,
        )
    }
}