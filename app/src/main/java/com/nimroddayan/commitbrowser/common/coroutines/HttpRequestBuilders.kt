/*
 * Copyright 2020 Nimrod Dayan nimroddayan.com
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

package com.nimroddayan.commitbrowser.common.coroutines

import kotlinx.coroutines.delay
import timber.log.Timber

suspend fun <T> withRetry(
    times: Int = 3,
    delayMs: Long = 3000L,
    onError: suspend (throwable: Throwable) -> Unit = { _ -> delay(delayMs) },
    block: suspend () -> T
): T {
    for (i in times..1) {
        try {
            return block()
        } catch (e: Exception) {
            onError(e)
        }
    }
    return block()
}

suspend fun <T> withInternet(
    waitForInternet: suspend () -> Unit,
    retryAfterError: (throwable: Throwable) -> Boolean,
    block: suspend () -> T
): T {
    var retry: Boolean
    do {
        try {
            Timber.d("Trying request")
            return block()
        } catch (ex: Exception) {
            Timber.d(ex, "An error has occurred")
            retry = retryAfterError(ex)
            if (retry) {
                Timber.d("Waiting for internet connection")
                waitForInternet()
            } else {
                throw NonRetriableException(ex)
            }
        }
    } while (retry)
    throw IllegalStateException()
}

class NonRetriableException(throwable: Throwable) : Exception(throwable)
