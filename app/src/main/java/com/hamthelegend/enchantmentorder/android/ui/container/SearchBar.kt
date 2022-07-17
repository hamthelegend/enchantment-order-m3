package com.hamthelegend.enchantmentorder.android.ui.container

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.Clear
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.IconButton
import com.hamthelegend.enchantmentorder.composables.Updatable

@Composable
fun SearchBar(
    searchUpdatable: Updatable<String>,
    onStopSearching: () -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
) {
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = searchUpdatable.value,
        onValueChange = searchUpdatable.onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                maxLines = 1,
            )
        },
        leadingIcon = {
            IconButton(
                imageVector = ThemeIcons.ArrowBack,
                contentDescription = stringResource(id = R.string.stop_searching),
                onClick = {
                    onStopSearching()
                    searchUpdatable.onValueChange("")
                },
            )
        },
        trailingIcon = {
            if (searchUpdatable.value.isNotEmpty()) {
                IconButton(
                    imageVector = ThemeIcons.Clear,
                    contentDescription = stringResource(id = R.string.clear_search),
                    onClick = { searchUpdatable.onValueChange("") },
                )
            }
        },
        singleLine = true,
    )

    LaunchedEffect(visible) {
        if (visible) focusRequester.requestFocus()
    }
}