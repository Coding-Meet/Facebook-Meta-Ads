package com.coding.meet.facebookmetaads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ShowNativeAdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_native_ad)

        val myNativeAds = MyNativeAds(this)
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        myNativeAds.loadNativeAd(
            findViewById(R.id.nativeAdLayout),
            true,
            "YOUR_PLACEMENT_ID"
        )
    }
}