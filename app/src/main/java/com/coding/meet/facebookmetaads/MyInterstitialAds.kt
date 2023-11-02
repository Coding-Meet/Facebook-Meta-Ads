package com.coding.meet.facebookmetaads

import android.app.Activity
import com.facebook.ads.*

class MyInterstitialAds(private val activity: Activity) {
    private var interstitialAds : InterstitialAd? = null

    fun loadInterstitialAds(placementId:String){
        interstitialAds = InterstitialAd(activity,placementId)
        interstitialAds?.loadAd()
    }

    fun showInterstitialAds(afterSomeCode: () -> Unit) {
        if (interstitialAds != null && interstitialAds?.isAdLoaded == true){
            interstitialAds?.buildLoadAdConfig()?.withAdListener(object : InterstitialAdListener{
                override fun onError(ad: Ad?, adError: AdError?) {
                    activity.longToastShow("${adError?.errorMessage}")
                }

                override fun onAdLoaded(ad: Ad?) {
                    activity.longToastShow("onAdLoaded")
                }

                override fun onAdClicked(ad: Ad?) {
                    activity.longToastShow("onAdClicked")
                }

                override fun onLoggingImpression(ad: Ad?) {
                    activity.longToastShow("onLoggingImpression")
                }

                override fun onInterstitialDisplayed(ad: Ad?) {
                    activity.longToastShow("onInterstitialDisplayed")
                }

                override fun onInterstitialDismissed(ad: Ad?) {
                    interstitialAds = null
                    afterSomeCode()
                }

            })?.build()
            interstitialAds?.show()
        }else{
            afterSomeCode()
        }

    }

}