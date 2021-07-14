package es.plexus.android.data_layer.networkmanager

import android.content.Context
import es.plexus.android.data_layer.extensions.isNetworkAvailable

interface NetworkManager {

    companion object{
        const val NETWORK_MANAGER_TAG = "networkManagerTag"
    }
    suspend fun isNetworkAvailable(): Boolean
}

class NetworkManagerImpl(private val context: Context) :  NetworkManager{

    override suspend  fun isNetworkAvailable(): Boolean = context.isNetworkAvailable()
}