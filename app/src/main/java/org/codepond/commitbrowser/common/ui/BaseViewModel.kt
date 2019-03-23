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
    val loadingState: LiveData<LoadingState>
        get() = _loadingState
    private val _loadingState = MutableLiveData<LoadingState>()

    fun notifyLoading() {
        _loadingState.value = LoadingState.Loading
    }

    fun <T> notifyLoaded(data: T) {
        _loadingState.value = LoadingState.Loaded(data)
    }

    fun notifyError(throwable: Throwable) {
        _loadingState.value = LoadingState.Error(throwable)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("ViewModel is destroyed")
    }

    sealed class LoadingState {
        object Loading : LoadingState()
        class Error(val throwable: Throwable) : LoadingState()
        class Loaded<T>(val data: T) : LoadingState()
    }
}
