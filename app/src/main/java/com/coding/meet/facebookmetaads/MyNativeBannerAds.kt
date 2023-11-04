package com.coding.meet.facebookmetaads

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.facebook.ads.*

class MyNativeBannerAds(private val activity: Activity) {

    fun loadNativeBannerAds(
        nativeAdLayout : NativeAdLayout,
        type: NativeBannerAdView.Type,
        isCustomLayout : Boolean,
        placementId : String
    ){
        val nativeBannerAd = NativeBannerAd(activity,placementId)
        val nativeAdListener = object : NativeAdListener {
            override fun onError(ad: Ad?, adError: AdError?) {
                activity.longToastShow("${adError?.errorMessage}")
            }
            override fun onAdLoaded(ad: Ad?) {
                if (isCustomLayout){
                    customNativeBannerLayout(nativeBannerAd, nativeAdLayout)
                }else {
                    val viewAttributes = NativeAdViewAttributes(activity)
                        .setBackgroundColor(Color.BLACK)
                        .setTitleTextColor(Color.WHITE)
                        .setDescriptionTextColor(Color.LTGRAY)
                        .setButtonColor(Color.WHITE)
                        .setButtonTextColor(Color.BLACK)

                    val adView = NativeBannerAdView.render(
                        activity,
                        nativeBannerAd,
                        type,
                        viewAttributes
                    )
                    nativeAdLayout.addView(adView)
                }
            }
            override fun onAdClicked(ad: Ad?) {}
            override fun onLoggingImpression(ad: Ad?) {}
            override fun onMediaDownloaded(ad: Ad?) {}

        }
        nativeBannerAd.loadAd(
            nativeBannerAd.buildLoadAdConfig()
                .withAdListener(nativeAdListener)
                .build()
        )
    }
    private fun customNativeBannerLayout(nativeBannerAd: NativeBannerAd, nativeAdLayout: NativeAdLayout) {

        // Unregister last ad
        nativeBannerAd.unregisterView()

        // Inflate the Ad view.  The layout referenced is the one you created in the last step.
        val adView = LayoutInflater.from(activity).inflate(
            R.layout.native_banner_ad_container,
            nativeAdLayout,
            false
        )
        nativeAdLayout.addView(adView)

        // Add the AdChoices icon
        val adChoicesContainer = adView.findViewById<RelativeLayout>(R.id.ad_choices_container)
        val adOptionsView =
            AdOptionsView(activity, nativeBannerAd, nativeAdLayout)
        adChoicesContainer.removeAllViews()
        adChoicesContainer.addView(adOptionsView, 0)

        // Create native UI using the ad metadata.
        val nativeAdTitle = adView.findViewById<TextView>(R.id.native_ad_title)
        val nativeAdSocialContext = adView.findViewById<TextView>(R.id.native_ad_social_context)
        val sponsoredLabel = adView.findViewById<TextView>(R.id.native_ad_sponsored_label)
        val nativeAdIconView = adView.findViewById<MediaView>(R.id.native_icon_view)
        val nativeAdCallToAction = adView.findViewById<Button>(R.id.native_ad_call_to_action)

        // Set the Text.
        nativeAdCallToAction.text = nativeBannerAd.adCallToAction
        nativeAdCallToAction.visibility =
            if (nativeBannerAd.hasCallToAction()) View.VISIBLE else View.INVISIBLE
        nativeAdTitle.text = nativeBannerAd.advertiserName
        nativeAdSocialContext.text = nativeBannerAd.adSocialContext
        sponsoredLabel.text = nativeBannerAd.sponsoredTranslation

        // Register the Title and CTA button to listen for clicks.
        val clickableViews = ArrayList<View>()
        clickableViews.add(nativeAdTitle)
        clickableViews.add(nativeAdCallToAction)
        nativeBannerAd.registerViewForInteraction(adView, nativeAdIconView, clickableViews)
    }

}