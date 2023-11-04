package com.coding.meet.facebookmetaads

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.ads.*


class MainActivity : AppCompatActivity() {

    private var adView: AdView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // NOTE: The placement ID from the Facebook Monetization Manager identifies your App.
        // To get test ads, add IMG_16_9_APP_INSTALL# to your placement id. Remove this when your app is ready to serve real ads.
        // AdSize.BANNER_HEIGHT_50
        adView = showBannerAds(
            this,
            findViewById(R.id.adBannerContainer), "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50
        )

        // AdSize.BANNER_HEIGHT_90
//        adView = showBannerAds(
//            this,
//            findViewById(R.id.adBannerContainer), "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_90
//        )
//

//        // AdSize.RECTANGLE_HEIGHT_250
//        adView = showBannerAds(
//            this,
//            findViewById(R.id.adBannerContainer), "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.RECTANGLE_HEIGHT_250
//        )


        val showInterstitialAdsBtn = findViewById<Button>(R.id.showInterstitialAdsBtn)

        val myInterstitialAds = MyInterstitialAds(this)

        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
        myInterstitialAds.loadInterstitialAds("YOUR_PLACEMENT_ID")

        showInterstitialAdsBtn.setOnClickListener {
            myInterstitialAds.showInterstitialAds {
                val afterIntent = Intent(this,AfterInterstitialActivity::class.java)
                startActivity(afterIntent)
            }
        }

        val showNativeAdsBtn = findViewById<Button>(R.id.showNativeAdsBtn)

        showNativeAdsBtn.setOnClickListener {
            val showNativeIntent = Intent(this,ShowNativeAdActivity::class.java)
            startActivity(showNativeIntent)
        }

        val showNativeBannerAdsBtn = findViewById<Button>(R.id.showNativeBannerAdsBtn)
        showNativeBannerAdsBtn.setOnClickListener {
            val showNativeBannerIntent = Intent(this,ShowNativeBannerAdActivity::class.java)
            startActivity(showNativeBannerIntent)
        }

    }

    override fun onDestroy() {
        adView?.destroy()
        super.onDestroy()
    }
}