package com.hamthelegend.enchantmentorder.android.ui.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient.*
import com.android.billingclient.api.BillingClient.BillingResponseCode.OK
import com.hamthelegend.enchantmentorder.android.EnchantmentOrderApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val application: Application,
) : ViewModel() {

    private val premiumSubscriptionId = "premium_subscription"

    private var subscriptionProductDetails: ProductDetails? by mutableStateOf(null)

    var premium: Boolean? by mutableStateOf(null)
        private set

    var connectionFailed by mutableStateOf(false)
        private set

    var subscriptionPrice: String? by mutableStateOf(null)
        private set

    private val purchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            viewModelScope.launch {
                checkSubscription(billingResult, purchases)
            }
        }

    private val billingClient = newBuilder(application)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()

    init {
        startConnection()
    }

    fun onResume() {
        viewModelScope.launch {
            queryProductDetails()
            queryPurchases()
        }
    }

    fun purchase() {
        subscriptionProductDetails?.let { productDetails ->

            val activity = (application as EnchantmentOrderApplication).mainActivity

            val paramsList = listOf(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(productDetails)
                    .setOfferToken(productDetails.subscriptionOfferDetails!!.first().offerToken)
                    .build()
            )

            val params = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(paramsList)
                .build()

            billingClient.launchBillingFlow(activity, params)
        }
    }

    private fun startConnection() {
        billingClient.startConnection(
            object : BillingClientStateListener {

                override fun onBillingSetupFinished(result: BillingResult) {
                    if (result.responseCode == OK) {
                        viewModelScope.launch {
                            queryProductDetails()
                            queryPurchases()
                        }
                    }
                }

                override fun onBillingServiceDisconnected() {
                    connectionFailed = true
                }

            }
        )
    }

    private suspend fun queryProductDetails() {
        withContext(Dispatchers.IO) {
            val paramsList = listOf(
                QueryProductDetailsParams.Product.newBuilder()
                    .setProductId(premiumSubscriptionId)
                    .setProductType(ProductType.SUBS)
                    .build()
            )
            val params = QueryProductDetailsParams.newBuilder()
                .setProductList(paramsList)
                .build()
            val productDetailsResult = withContext(Dispatchers.IO) {
                billingClient.queryProductDetails(params)
            }
            if (productDetailsResult.billingResult.responseCode == OK &&
                !productDetailsResult.productDetailsList.isNullOrEmpty()
            ) {
                subscriptionProductDetails = productDetailsResult.productDetailsList?.get(0)
                subscriptionPrice = subscriptionProductDetails
                    ?.subscriptionOfferDetails?.get(0)
                    ?.pricingPhases
                    ?.pricingPhaseList?.get(0)
                    ?.formattedPrice
            }
        }
    }

    private suspend fun queryPurchases() {
        withContext(Dispatchers.IO) {
            val params = QueryPurchasesParams.newBuilder()
                .setProductType(ProductType.SUBS)
            val purchasesResult = billingClient.queryPurchasesAsync(params.build())
            checkSubscription(purchasesResult.billingResult, purchasesResult.purchasesList)
        }
    }

    private suspend fun checkSubscription(
        billingResult: BillingResult,
        purchases: List<Purchase>?,
    ) {
        withContext(Dispatchers.IO) {
            if (billingResult.responseCode == OK) {
                if (purchases.isNullOrEmpty()) {
                    premium = false
                } else {
                    for (purchase in purchases) {
                        if (
                            purchase.purchaseState == Purchase.PurchaseState.PURCHASED
                            && purchase.products.getOrNull(0) == premiumSubscriptionId
                        ) {
                            handlePurchase(purchase)
                            premium = true
                        }
                    }
                }
            }
        }
    }

    private suspend fun handlePurchase(purchase: Purchase, triesLeft: Int = 10) {
        withContext(Dispatchers.IO) {
            if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                if (!purchase.isAcknowledged) {
                    val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.purchaseToken)
                    val acknowledgePurchaseResult = withContext(Dispatchers.IO) {
                        billingClient.acknowledgePurchase(acknowledgePurchaseParams.build())
                    }
                    if (acknowledgePurchaseResult.responseCode != OK) {
                        handlePurchase(purchase, triesLeft - 1)
                    }
                }
            }
        }
    }

}