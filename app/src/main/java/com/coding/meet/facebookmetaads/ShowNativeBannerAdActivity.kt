package com.coding.meet.facebookmetaads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.ads.NativeBannerAd
import com.facebook.ads.NativeBannerAdView

class ShowNativeBannerAdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_native_banner_ad)

        val myNativeBannerAds = MyNativeBannerAds(this)
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // NativeBannerAdView.Type.HEIGHT_50 ,HEIGHT_100, HEIGHT_120
        myNativeBannerAds.loadNativeBannerAds(
            findViewById(R.id.nativeBannerAd),
            NativeBannerAdView.Type.HEIGHT_120,
            true,
            "YOUR_PLACEMENT_ID"
        )
    }
}