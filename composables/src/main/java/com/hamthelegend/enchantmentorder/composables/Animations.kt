package com.hamthelegend.enchantmentorder.composables

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
fun sharedZAxisExit(fromFront: Boolean = false) =
    scaleOut(
        animationSpec = tween(durationMillis = 300),
        targetScale = if (!fromFront) 1.1f else 0.8f,
    ) + fadeOut(
        animationSpec = tween(durationMillis = 90),
    )

@OptIn(ExperimentalAnimationApi::class)
fun sharedZAxisEnter(fromFront: Boolean = false) =
    scaleIn(
        animationSpec = tween(durationMillis = 300),
        initialScale = if (!fromFront) 0.8f else 1.1f,
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 210,
            delayMillis = 90,
        ),
    )

fun sharedXAxisExit(density: Density, fromStart: Boolean = false) =
    slideOutHorizontally(
        animationSpec = tween(durationMillis = 300),
        targetOffsetX = { with(density) { (if (!fromStart) -30 else 30).dp.roundToPx() } },
    ) + fadeOut(
        animationSpec = tween(durationMillis = 90),
    )

fun sharedXAxisEnter(density: Density, fromStart: Boolean = false) =
    slideInHorizontally(
        animationSpec = tween(durationMillis = 300),
        initialOffsetX = { with(density) { (if (!fromStart) 30 else -30).dp.roundToPx() } }
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 210,
            delayMillis = 90,
        ),
    )

fun sharedYAxisExit(density: Density, fromTop: Boolean = false) =
    slideOutVertically(
        animationSpec = tween(durationMillis = 300),
        targetOffsetY = { with(density) { (if (!fromTop) -30 else 30).dp.roundToPx() } },
    ) + fadeOut(
        animationSpec = tween(durationMillis = 90),
    )

fun sharedYAxisEnter(density: Density, fromTop: Boolean = false) =
    slideInVertically(
        animationSpec = tween(durationMillis = 300),
        initialOffsetY = { with(density) { (if (!fromTop) 30 else -30).dp.roundToPx() } }
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 210,
            delayMillis = 90,
        ),
    )

@OptIn(ExperimentalAnimationApi::class)
fun <S> crossfadeTransition(): AnimatedContentScope<S>.() -> ContentTransform = {
    ContentTransform(
        targetContentEnter = fadeIn(),
        initialContentExit = fadeOut()
    )
}