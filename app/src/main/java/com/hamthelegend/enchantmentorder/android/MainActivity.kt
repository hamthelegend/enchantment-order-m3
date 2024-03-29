package com.hamthelegend.enchantmentorder.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hamthelegend.enchantmentorder.android.ui.screens.SubscriptionViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.navhost.NavHost
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val subscriptionViewModel by viewModels<SubscriptionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as EnchantmentOrderApplication).mainActivity = this

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            EnchantmentOrderTheme {
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()

                SideEffect {
                    // Update all of the system bar colors to be transparent, and use
                    // dark icons if we're in light theme
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons
                    )
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(this, subscriptionViewModel)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        subscriptionViewModel.onResume()
    }
}