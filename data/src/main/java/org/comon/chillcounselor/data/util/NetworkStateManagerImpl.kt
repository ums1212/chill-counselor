package org.comon.chillcounselor.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import org.comon.chillcounselor.domain.util.NetworkStateManager

class NetworkStateManagerImpl(
    private val context: Context
): NetworkStateManager {

    override fun checkNetworkState(): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(ConnectivityManager::class.java)
        val network: Network = connectivityManager.activeNetwork ?: return false
        val actNetwork: NetworkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            else -> false
        }
    }

}