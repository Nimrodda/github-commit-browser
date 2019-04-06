/*
 * Copyright 2019 Nimrod Dayan nimroddayan.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codepond.commitbrowser.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.ContextCompat
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume

/**
 * Interface for querying internet connection state
 */
interface InternetConnection {
    /**
     * Suspends execution until internet connection is available
     */
    suspend fun waitForInternet()
}

/**
 * Implementation of [InternetConnection] that uses Android's [ConnectivityManager] to query for available
 * internet connection.
 *
 * Execution is suspended until [ConnectivityManager.NetworkCallback] onAvailable() is called.
 *
 * @param context instance of [Context]
 */
class InternetConnectionState @Inject constructor(
    context: Context
) : InternetConnection {
    private val appContext: Context = context.applicationContext

    override suspend fun waitForInternet(): Unit = suspendCancellableCoroutine { continuation ->
        val connectivityManager = ContextCompat.getSystemService(appContext, ConnectivityManager::class.java)
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network?) {
                Timber.d("Internet available")
                connectivityManager?.unregisterNetworkCallback(this)
                continuation.resume(Unit)
            }
        }
        connectivityManager?.registerNetworkCallback(networkRequest, networkCallback)
        continuation.invokeOnCancellation { connectivityManager?.unregisterNetworkCallback(networkCallback) }
    }
}
