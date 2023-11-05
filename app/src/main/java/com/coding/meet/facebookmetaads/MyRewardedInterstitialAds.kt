package com.coding.meet.facebookmetaads

import android.app.Activity
import com.facebook.ads.*

class MyRewardedInterstitialAds(private val activity: Activity) {
    private var rewardedInterstitialAd : RewardedInterstitialAd? = null


    fun loadRewardedInterstitialAds(placementId : String){
        rewardedInterstitialAd = RewardedInterstitialAd(activity,placementId)
        rewardedInterstitialAd?.loadAd()
    }

    fun showRewardedInterstitialAds(placementId: String, afterRewardedCoin : () -> Unit ){
        if (rewardedInterstitialAd != null && rewardedInterstitialAd?.isAdLoaded == true){
            rewardedInterstitialAd?.buildLoadAdConfig()?.withAdListener(object : RewardedInterstitialAdListener {
                override fun onError(ad: Ad?, adError: AdError?) {
                    activity.longToastShow("${adError?.errorMessage}")
                }

                override fun onAdLoaded(ad: Ad?) {}

                override fun onAdClicked(ad: Ad?) {}

                override fun onLoggingImpression(ad: Ad?) {}

                override fun onRewardedInterstitialCompleted() {
                    rewardedInterstitialAd = null
                }

                override fun onRewardedInterstitialClosed() {
                    rewardedInterstitialAd = null
                    loadRewardedInterstitialAds(placementId)
                    afterRewardedCoin()
                }

            })?.build()
            rewardedInterstitialAd?.show()
        }else{
            activity.longToastShow("Ads is not Loaded")
        }
    }
}