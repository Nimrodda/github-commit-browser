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

    fun notifyLoading() {
        _loadingState.value = ViewState.Loading
    }

    fun <T> notifyLoaded(data: T) {
        _loadingState.value = ViewState.Loaded(data)
    }

    fun notifyError(throwable: Throwable) {
        _loadingState.value = ViewState.Error(throwable)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("ViewModel is destroyed")
    }

    sealed class ViewState {
        object Loading : ViewState()
        class Error(val throwable: Throwable) : ViewState()
        class Loaded<T>(val data: T) : ViewState()
    }
}
