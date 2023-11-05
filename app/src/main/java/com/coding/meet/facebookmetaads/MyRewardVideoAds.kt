package com.coding.meet.facebookmetaads

import android.app.Activity
import com.facebook.ads.*

class MyRewardVideoAds(private val activity: Activity) {
    private var rewardedVideoAd : RewardedVideoAd? = null

    fun loadRewardedVideoAds(placementId:String){
        rewardedVideoAd = RewardedVideoAd(activity,placementId)
        rewardedVideoAd?.loadAd()
    }

    fun showRewardVideoAds(placementId: String,afterRewardedCode: () -> Unit){

        if (rewardedVideoAd != null && rewardedVideoAd?.isAdLoaded == true){
            rewardedVideoAd?.buildLoadAdConfig()?.withAdListener(object: RewardedVideoAdListener {
                override fun onError(ad: Ad?, adError: AdError?) {
                    activity.longToastShow("${adError?.errorMessage}")
                }

                override fun onAdLoaded(ad: Ad?) {}

                override fun onAdClicked(ad: Ad?) {}

                override fun onLoggingImpression(ad: Ad?) {}

                override fun onRewardedVideoCompleted() {
                    rewardedVideoAd = null
                }

                override fun onRewardedVideoClosed() {
                    rewardedVideoAd = null
                    loadRewardedVideoAds(placementId)
                    afterRewardedCode()
                }

            })?.build()
            rewardedVideoAd?.show()
        }else{
            activity.longToastShow("Ads is not Loaded")
        }
    }
}