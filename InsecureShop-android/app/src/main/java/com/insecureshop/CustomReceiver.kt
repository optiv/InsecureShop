package com.insecureshop

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CustomReceiver : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        val stringExtra = intent?.extras?.getString("web_url")
        if (!stringExtra.isNullOrBlank()) {
            val intent = Intent(context, WebView2Activity::class.java)
            intent.putExtra("url",stringExtra)
            context?.startActivity(intent)
        }
    }

}