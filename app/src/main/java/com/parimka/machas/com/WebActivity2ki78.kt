package com.parimka.machas.com

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.parimka.machas.com.Utilities2ki78.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WebActivity2ki78 : AppCompatActivity() {

    private lateinit var wv2ki78: WebView
    private lateinit var view2ki782: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_activity2ki78)
        view2ki782 = findViewById(R.id.v_web_2ki782)
        setUpWebViewActivity2ki78()
        circleChecker2ki78()
    }

    override fun onBackPressed() =
        if (wv2ki78.canGoBack() && wv2ki78.isFocused) wv2ki78.goBack() else super.onBackPressed()

    override fun onActivityResult(requestCode2ki78: Int, resultCode2ki78: Int, data2ki78: Intent?) {
        if (filePathCallBack2ki78 != null && requestCode2ki78 == 0) {
            val uriResult2ki78 =
                if (data2ki78 == null || resultCode2ki78 != RESULT_OK) null else data2ki78.data
            if (uriResult2ki78 != null) {
                filePathCallBack2ki78?.onReceiveValue(arrayOf(uriResult2ki78))
            } else {
                filePathCallBack2ki78?.onReceiveValue(arrayOf(uriView2ki78))
            }
            filePathCallBack2ki78 = null
        }
        super.onActivityResult(requestCode2ki78, resultCode2ki78, data2ki78)
    }

    private fun splashAnimation2ki78() {
        findViewById<View>(R.id.v_web_2ki782).let { circleWeb2ki78 ->
            AnimatorSet().play(
                ObjectAnimator.ofPropertyValuesHolder(
                    circleWeb2ki78, PropertyValuesHolder.ofFloat(
                        View.SCALE_X, 1f, 0.5f
                    ), PropertyValuesHolder.ofFloat(
                        View.SCALE_Y, 1f, 0.5f
                    )
                ).apply {
                    duration = 500
                    start()
                }
            ).before(
                ObjectAnimator.ofFloat(circleWeb2ki78, View.ALPHA, 1f, 0f).apply {
                    duration = 500
                    start()
                }
            ).with(
                ObjectAnimator.ofPropertyValuesHolder(
                    circleWeb2ki78, PropertyValuesHolder.ofFloat(
                        View.SCALE_X, 1f, 25f
                    ), PropertyValuesHolder.ofFloat(
                        View.SCALE_Y, 1f, 25f
                    )
                ).apply {
                    duration = 500
                    start()
                }
            )

        }
    }

    private fun returnCircle2ki78() {
        findViewById<View>(R.id.v_web_2ki782).run {
            animate().alpha(1f).run { duration = 500 }
            animate().scaleX(1f).run { duration = 500 }
            animate().scaleY(1f).run { duration = 500 }
        }
    }

    private fun WebActivity2ki78.circleChecker2ki78() {
        lifecycleScope.launch {
            delay(500)
            splashAnimation2ki78()
        }
    }

    private fun setUpWebViewActivity2ki78() {
        wv2ki78 = findViewById(R.id.wv_2ki78)
        wv2ki78.run {
            settings.run {
                loadWithOverviewMode = true
                displayZoomControls = false
                useWideViewPort = true
                javaScriptEnabled = true
                loadsImagesAutomatically = true
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                builtInZoomControls = true
                displayZoomControls = false
                domStorageEnabled = true
                mediaPlaybackRequiresUserGesture = false
            }

            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view2ki78: WebView?,
                    request2ki78: WebResourceRequest?
                ): Boolean {
                    if (!internetChecker2ki78()) {
                        lifecycleScope.launch {
                            visibility = View.INVISIBLE
                            returnCircle2ki78()
                            delay(500)
                            startActivity(
                                Intent(
                                    this@WebActivity2ki78,
                                    InternetConnectionActivity2ki78::class.java
                                ),
                                ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    this@WebActivity2ki78,
                                    view2ki782,
                                    "view_internet"
                                ).toBundle()
                            )
                            finish()
                        }
                    }
                    val prohibitedLinks2ki78 = listOf("facebook", "twitter")
                    val modifiedLinks2ki78 = listOf("mailto:", "tel:")
                    return when {
                        prohibitedLinks2ki78.find {
                            request2ki78?.url.toString().contains(it)
                        } != null -> true
                        modifiedLinks2ki78.find {
                            request2ki78
                                ?.url.toString().startsWith(it)
                        } != null -> {
                            view2ki78?.context?.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    request2ki78?.url
                                )
                            )
                            true
                        }
                        else -> false
                    }


                }

                override fun onPageFinished(view2ki78: WebView?, url2ki78: String?) {
                    getSharedPreferences("SP_2ki78", MODE_PRIVATE).edit()
                        .putString("Last_Page_2ki78", url2ki78 ?: return).apply()
                    super.onPageFinished(view2ki78, url2ki78)
                }

                override fun onReceivedSslError(
                    view2ki78: WebView?,
                    handler2ki78: SslErrorHandler?,
                    error2ki78: SslError?
                ) {
                    handler2ki78?.proceed()
                    super.onReceivedSslError(view2ki78, handler2ki78, error2ki78)

                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onShowFileChooser(
                    webView2ki78: WebView?,
                    filePathCallback2ki78s: ValueCallback<Array<Uri>>?,
                    fileChooserParams2ki78: FileChooserParams?
                ): Boolean {
                    checkPermissions2ki78()
                    filePathCallBack2ki78 = filePathCallback2ki78s
                    val captureIntent2ki78 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    if (captureIntent2ki78.resolveActivity(packageManager) != null) {
                        val providedFile2ki78 = createTempFile2ki78()
                        uriView2ki78 = FileProvider.getUriForFile(
                            this@WebActivity2ki78,
                            "${packageName}.provider",
                            providedFile2ki78
                        )
                        captureIntent2ki78.run {
                            putExtra(MediaStore.EXTRA_OUTPUT, uriView2ki78)
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }
                        val actionIntent2ki78 = Intent(Intent.ACTION_GET_CONTENT).apply {
                            addCategory(Intent.CATEGORY_OPENABLE)
                            type = "image/*"
                        }
                        val intentChooser2ki78 = Intent(Intent.ACTION_CHOOSER).apply {
                            putExtra(Intent.EXTRA_INTENT, captureIntent2ki78)
                            putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(actionIntent2ki78))
                        }
                        startActivityForResult(intentChooser2ki78, 0)
                        return true

                    }
                    return super.onShowFileChooser(
                        webView2ki78,
                        filePathCallback2ki78s,
                        fileChooserParams2ki78
                    )
                }
            }
            getSharedPreferences("SP_2ki78", MODE_PRIVATE).getString("Last_Page_2ki78", null)
                ?.let {
                    loadUrl(it)
                    return@run
                }
            loadUrl(parsedURL2ki78 ?: return)

        }

        findViewById<SwipeRefreshLayout>(R.id.srl_2ki78)?.let {
            it.setOnRefreshListener {
                wv2ki78.loadUrl(wv2ki78.url ?: return@setOnRefreshListener)
                it.isRefreshing = false
            }
        }
    }
}