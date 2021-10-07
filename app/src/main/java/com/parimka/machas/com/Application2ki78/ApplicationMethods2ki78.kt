package com.parimka.machas.com.Application2ki78

import android.content.Context
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import com.parimka.machas.com.Utilities2ki78.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Context.mobileAdsSetup2ki78 () {
    MobileAds.initialize(this)
    CoroutineScope(Dispatchers.IO).launch {
        try {
            AID2ki78 =
                AdvertisingIdClient.getAdvertisingIdInfo(this@mobileAdsSetup2ki78)?.id
        } catch (e2ki78: Exception) {

        }
    }
}

fun Context.oneSignalSetup2ki78 () {
    OneSignal.initWithContext(this)
    OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
    OneSignal.setAppId(CODED_ONESIGNAL_2ki78.decodeStrings2ki78())
}

fun Context.appsFlyerSetup2ki78 () {
    val appsFlyerConversion2ki78 = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(dataMap2ki78: MutableMap<String, Any>?) {
            dataMap2ki78?.run {

                status2ki78 =
                    if (getValue(APPSFLYER_STATUS_TAG_2ki78).toString() == "Organic") "Organic" else "Non-organic"

                val paramsArray2ki78 = mutableListOf<String>()
                getValue(APPSFLYER_CAMPAIGN_TAG_2ki78)
                    .toString()
                    .split("||").drop(1)
                    .map {
                        paramsArray2ki78.add(it.split(":")[1])
                    }

                key2ki78 = if (paramsArray2ki78.size >= 1) paramsArray2ki78[0] else "empty"
                sub22ki78 = if (paramsArray2ki78.size >= 2) paramsArray2ki78[1] else "empty"
                sub32ki78 = if (paramsArray2ki78.size >= 3) paramsArray2ki78[2] else "empty"


                mediaSource2ki78 = getValue(APPSFLYER_MEDIA_SOURCE_TAG_2ki78).toString()
                afChannel2ki78 = getValue(APPSFLYER_AF_CHANNEL_TAG_2ki78).toString()
                adGroup2ki78 = getValue(APPSFLYER_ADGROUP_TAG_2ki78).toString()
                adSet22ki78 = getValue(APPSFLYER_ADSET_TAG_2ki78).toString()



            }
        }

        override fun onConversionDataFail(p0: String?) {
        }

        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
        }

        override fun onAttributionFailure(p0: String?) {
        }
    }
    AppsFlyerLib.getInstance().run {
        uid2ki78 = getAppsFlyerUID(this@appsFlyerSetup2ki78)
        init(
            CODE_APPSFLYER_2ki78.decodeStrings2ki78(),
            appsFlyerConversion2ki78,
            this@appsFlyerSetup2ki78
        )
        start(this@appsFlyerSetup2ki78)
    }
}