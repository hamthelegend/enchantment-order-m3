package com.hamthelegend.enchantmentorder.android

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

const val TestBannerAdUnitId = "ca-app-pub-3940256099942544/6300978111"
const val MyBannerAdUnitId = "ca-app-pub-4910427575758293/8515776813"

const val TestInterstitialAdUnitId = "ca-app-pub-3940256099942544/1033173712"
const val MyInterstitialAdUnitId = "ca-app-pub-4910427575758293/9405723634"

const val ActiveBannerAdUnitId = MyBannerAdUnitId
const val ActiveInterstitialAdUnitId = MyInterstitialAdUnitId

var interstitialAd: InterstitialAd? = null

fun Context.loadInterstitialAd() {
    InterstitialAd.load(
        this,
        ActiveInterstitialAdUnitId,
        AdRequest.Builder().build(),
        object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                interstitialAd = null
                Log.d("MainActivity", adError.message)
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                com.hamthelegend.enchantmentorder.android.interstitialAd = interstitialAd
                Log.d("MainActivity", "Ad was loaded.")
            }
        }
    )
}

fun Context.showInterstitialAd() {
    val activity = this as MainActivity

    if (interstitialAd != null) {
        interstitialAd?.show(activity)
    } else {
        Log.d("MainActivity", "The interstitial ad wasn't ready yet.")
    }
}