package com.insecureshop

import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.insecureshop.util.CustomWebViewClient
import com.insecureshop.util.Prefs
import kotlinx.android.synthetic.main.activity_product_list.*


class WebViewActivity : AppCompatActivity() {

    val USER_AGENT =
        "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.65 Mobile Safari/537.36"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        setSupportActionBar(toolbar)
        title = getString(R.string.webview)

        val webview = findViewById<WebView>(R.id.webview)

        webview.settings.javaScriptEnabled = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
        webview.settings.allowUniversalAccessFromFileURLs = true
        webview.settings.userAgentString = USER_AGENT
        webview.webViewClient = CustomWebViewClient()
        val uri : Uri? = intent.data
        uri?.let {
            var data: String? = null
            if (uri.path.equals("/web")) {
                data = intent.data?.getQueryParameter("url")
            } else if (uri.path.equals("/webview")) {
                if (intent.data!!.getQueryParameter("url")!!.endsWith("insecureshopapp.com")) {
                    data = intent.data?.getQueryParameter("url")
                }
            }

            if (data == null) {
                finish()
            }
            webview.loadUrl(data)
            Prefs.getInstance(this).data = data
        }

    }


}
