package com.coding.meet.facebookmetaads

import android.app.Application
import com.facebook.ads.*

class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()
        AudienceNetworkAds.initialize(this)
        // if app release change to false
        AdSettings.setTestMode(true)
        // Example for setting the SDK to crash when in debug mode
        AdSettings.setIntegrationErrorMode(AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE)

    }
}