package com.hamthelegend.enchantmentorder.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EnchantmentOrderApplication : Application() {
    lateinit var mainActivity: MainActivity
}