package com.hamthelegend.enchantmentorder.android.ui.container

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.composables.Updatable
import com.hamthelegend.enchantmentorder.composables.rememberDerivedStateOf
import com.hamthelegend.enchantmentorder.composables.rememberMutableStateOf
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
        modifier = modifier.navigationBarsPadding(),
        topBar = {
            TopBar(
                title = title,
                navigateUp = navigateUp,
                searchUpdatable = searchUpdatable,
                scrolled = scrolled,
                modifier = Modifier.statusBarsPadding(),
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
fun ContainerWithTopBarPreview() {
    EnchantmentOrderTheme {

        var searchQuery by rememberMutableStateOf(value = "")

        val lazyListState = rememberLazyListState()
        val scrolled by rememberDerivedStateOf {
            lazyListState.firstVisibleItemIndex != 0 ||
                    lazyListState.firstVisibleItemScrollOffset != 0
        }
        val scrollScope = rememberCoroutineScope()

        Container(
            title = "Enchantment Order",
            navigateUp = {
                scrollScope.launch {
                    lazyListState.animateScrollToItem(0, 0)
                }
            },
            searchUpdatable = Updatable(searchQuery) { searchQuery = it },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Container(
    modifier: Modifier = Modifier,
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier.systemBarsPadding(),
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
fun ContainerWithoutTopBarPreview() {
    EnchantmentOrderTheme {

        Container {
            LazyColumn(modifier = Modifier.fillMaxSize(), ) {
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