package com.mamadou.newsapp.networking

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NetworkStatusChecker(private val connectivityManager: ConnectivityManager?) {

    inline fun performIfConnectedToInternet(crossinline action: () -> Unit, crossinline notConnectedAction: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            if (hasInternetConnection()) {
                action()
            } else {
                notConnectedAction()
                println("********* There is no Internet Connection **********")
            }
        }

    }

    @SuppressLint("NewApi")
    fun hasInternetConnection(): Boolean {
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }
}