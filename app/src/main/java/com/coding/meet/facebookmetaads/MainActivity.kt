package com.coding.meet.facebookmetaads

import android.os.Bundle
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


    }

    override fun onDestroy() {
        adView?.destroy()
        super.onDestroy()
    }
}