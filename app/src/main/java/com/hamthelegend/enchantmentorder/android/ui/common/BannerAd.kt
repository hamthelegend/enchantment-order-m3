package com.hamthelegend.enchantmentorder.android.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.hamthelegend.enchantmentorder.android.ActiveBannerAdUnitId
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme

@Composable
fun BannerAd(modifier: Modifier = Modifier) {
    val isInEditMode = LocalInspectionMode.current
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 6.dp),
        textAlign = TextAlign.Center,
        text = stringResource(R.string.loading_ad),
        style = MaterialTheme.typography.bodySmall,
    )
    if (!isInEditMode) {
        AndroidView(
            modifier = modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = ActiveBannerAdUnitId
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}

@Preview
@Composable
fun BannerAdPreview() {
    EnchantmentOrderTheme {
        BannerAd()
    }
}