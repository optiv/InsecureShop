package com.insecureshop

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_product_list.*


class SendingDataViaActionActivity : AppCompatActivity() {

    val USER_AGENT =
        "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.65 Mobile Safari/537.36"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent_for_non_exported)
        setSupportActionBar(toolbar)
    }

    fun onSendData(view: View) {
        val intent = Intent("com.insecureshop.action.WEBVIEW")
        intent.putExtra("url", "https://www.insecureshop.com/")
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {

        }
    }

}
