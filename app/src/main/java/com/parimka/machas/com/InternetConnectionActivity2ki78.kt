package com.parimka.machas.com

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.animation.doOnEnd
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import com.parimka.machas.com.Utilities2ki78.internetChecker2ki78
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InternetConnectionActivity2ki78 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_connection_activity2ki78)
        lifecycleScope.launch {
            delay(500)
            ObjectAnimator.ofFloat(findViewById(R.id.tv_internet_2ki78), View.ALPHA, 0f, 1f)
                .run {
                    duration = 400
                    start()
                }
            ObjectAnimator.ofFloat(findViewById(R.id.b_internet_2ki78), View.ALPHA, 0f, 1f)
                .run {
                    duration = 400
                    start()
                }
        }
        findViewById<Button>(R.id.b_internet_2ki78).setOnClickListener {
            if (internetChecker2ki78()) {
                ObjectAnimator.ofFloat(findViewById(R.id.tv_internet_2ki78), View.ALPHA, 1f, 0f)
                    .run {
                        duration = 400
                        start()
                    }
                ObjectAnimator.ofFloat(findViewById(R.id.b_internet_2ki78), View.ALPHA, 1f, 0f)
                    .run {
                        duration = 400
                        start()
                        doOnEnd {
                            startActivity(
                                Intent(
                                    this@InternetConnectionActivity2ki78,
                                    WebActivity2ki78::class.java
                                ),
                                ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    this@InternetConnectionActivity2ki78,
                                    findViewById(R.id.v_web_2ki78),
                                    "view_in"
                                ).toBundle()
                            )
                        }
                    }
            }
        }
    }

    override fun onStop() {
        finish()
        super.onStop()
    }
}