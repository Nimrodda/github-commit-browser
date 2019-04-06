package org.codepond.commitbrowser.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

import org.codepond.commitbrowser.api.GithubApi
import org.codepond.commitbrowser.di.CoroutinesDispatcherProvider
import timber.log.Timber

abstract class BaseViewModel(
    protected val handle: SavedStateHandle,
    protected val githubApi: GithubApi,
    protected val dispatchers: CoroutinesDispatcherProvider
) : ViewModel() {
    val viewState: LiveData<ViewState>
        get() = _loadingState
    private val _loadingState = MutableLiveData<ViewState>()

    protected fun <T> notifyStateChanged(data: T) {
        _loadingState.value = ViewState.Changed(data)
    }

    private fun notifyError(throwable: Throwable) {
        _loadingState.postValue(ViewState.Error(throwable))
    }

    fun retryAfterError(throwable: Throwable): Boolean {
        notifyError(throwable)
        // Always retry no matter what is the error
        // Optionally, throwable can be inspected to determine which error to retry
        return true
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("ViewModel is destroyed")
    }

    sealed class ViewState {
        class Error(val throwable: Throwable) : ViewState()
        class Changed<T>(val data: T) : ViewState()
    }
}
