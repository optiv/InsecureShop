package com.insecureshop.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ProductDetailBroadCast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val webViewIntent = Intent("com.insecureshop.action.WEBVIEW")
        webViewIntent.putExtra("url","https://www.insecureshopapp.com/")
        context?.startActivity(webViewIntent)
    }
}