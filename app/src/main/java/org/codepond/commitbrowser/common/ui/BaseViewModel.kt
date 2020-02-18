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

package org.codepond.commitbrowser.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.codepond.commitbrowser.api.GithubApi
import org.codepond.commitbrowser.common.network.InternetConnection
import org.codepond.commitbrowser.di.CoroutinesDispatcherProvider
import org.codepond.commitbrowser.di.CoroutinesDispatchers
import timber.log.Timber

abstract class BaseViewModel<T>(
    protected val handle: SavedStateHandle,
    protected val githubApi: GithubApi,
    private val dispatchers: CoroutinesDispatcherProvider,
    private val internetConnection: InternetConnection
) : ViewModel(),
    InternetConnection by internetConnection,
    CoroutinesDispatchers by dispatchers {

    val viewState: LiveData<ViewState<T>>
        get() = _loadingState
    private val _loadingState = MutableLiveData<ViewState<T>>()

    protected fun notifyLoading(data: T) {
        _loadingState.postValue(ViewState.Loading(data))
    }

    protected fun notifyDataLoaded(data: T) {
        _loadingState.postValue(ViewState.Loaded(data))
    }

    protected fun notifyError(throwable: Throwable, data: T) {
        _loadingState.postValue(ViewState.Error(throwable, data))
    }

    fun retryAfterError(throwable: Throwable): Boolean {
        // Always retry no matter what is the error
        // Optionally, throwable can be inspected to determine which error to retry
        return true
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("ViewModel is destroyed")
    }
}

sealed class ViewState<T>(val data: T) {
    class Error<T>(val throwable: Throwable, data: T) : ViewState<T>(data)
    class Loading<T>(data: T) : ViewState<T>(data)
    class Loaded<T>(data: T) : ViewState<T>(data)

    fun isLoading(): Boolean = this is Loading
}
