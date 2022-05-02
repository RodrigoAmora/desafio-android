package com.picpay.desafio.android.util

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtil {
    companion object {
        fun checkConnection(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val mobileIsConnected = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.isConnected
            val wifiIsConnected = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.isConnected

            return !(!mobileIsConnected && !wifiIsConnected)
        }
    }
}