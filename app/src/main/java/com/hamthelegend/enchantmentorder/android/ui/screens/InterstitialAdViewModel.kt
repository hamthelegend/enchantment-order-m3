package com.hamthelegend.enchantmentorder.android.ui.screens

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.hamthelegend.enchantmentorder.android.ActiveInterstitialAdUnitId
import com.hamthelegend.enchantmentorder.android.EnchantmentOrderApplication
import com.hamthelegend.enchantmentorder.android.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InterstitialAdViewModel @Inject constructor(
    val application: Application,
): ViewModel() {

    var cachedAd: InterstitialAd? = null
    var interstitialAd: InterstitialAd? = null

    fun loadInterstitialAd() {
        InterstitialAd.load(
            (application as EnchantmentOrderApplication).mainActivity,
            ActiveInterstitialAdUnitId,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    if (interstitialAd != null) cachedAd = interstitialAd
                    interstitialAd = null
                    Log.d("MainActivity", adError.message)
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    this@InterstitialAdViewModel.interstitialAd = interstitialAd
                    Log.d("MainActivity", "Ad was loaded.")
                }
            }
        )
    }

    fun showInterstitialAd() {
        val mainActivity = (application as EnchantmentOrderApplication).mainActivity
        if (interstitialAd != null) {
            interstitialAd?.show(mainActivity)
        } else if (cachedAd != null) {
            cachedAd?.show(mainActivity)
        } else {
            Log.d("MainActivity", "The interstitial ad wasn't ready yet.")
        }
    }

}