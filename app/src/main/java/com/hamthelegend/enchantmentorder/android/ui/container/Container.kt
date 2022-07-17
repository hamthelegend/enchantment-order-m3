package com.hamthelegend.enchantmentorder.android.ui.container

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.composables.IconButton
import com.hamthelegend.enchantmentorder.composables.Updatable
import com.hamthelegend.enchantmentorder.composables.rememberMutableState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Container(
    title: String,
    modifier: Modifier = Modifier,
    navigateUp: (() -> Unit)? = null,
    searchUpdatable: Updatable<String>? = null,
    scrolled: Boolean = false,
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = title,
                navigateUp = navigateUp,
                searchUpdatable = searchUpdatable,
                scrolled = scrolled,
            )
        },
        floatingActionButton = floatingActionButton,
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
            content = content,
        )
    }
}

@Preview
@Composable
fun ContainerPreview() {
    EnchantmentOrderTheme {

        val lazyListState = rememberLazyListState()
        val scrolled by remember {
            derivedStateOf {
                lazyListState.firstVisibleItemIndex != 0 ||
                        lazyListState.firstVisibleItemScrollOffset != 0
            }
        }
        val scrollScope = rememberCoroutineScope()

        Container(
            title = "Enchantment Order",
            navigateUp = {
                scrollScope.launch {
                    lazyListState.animateScrollToItem(0, 0)
                }
            },
            searchUpdatable = Updatable("") {},
            scrolled = scrolled,
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
            ) {
                items((1..100).toList()) { item ->
                    Text(
                        text = item.toString(),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

