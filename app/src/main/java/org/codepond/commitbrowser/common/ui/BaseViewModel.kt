package org.codepond.commitbrowser.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

import org.codepond.commitbrowser.api.GithubApi

abstract class BaseViewModel(
    protected val handle: SavedStateHandle,
    protected val githubApi: GithubApi
) : ViewModel() {
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _isLoading = MutableLiveData<Boolean>()

    fun notifyLoading() {
        _isLoading.value = true
    }

    fun notifyLoaded() {
        _isLoading.value = false
    }
}

