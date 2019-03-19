package org.codepond.commitbrowser.commitdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.codepond.commitbrowser.api.GithubApi
import org.codepond.commitbrowser.common.ui.BaseViewModel
import org.codepond.commitbrowser.di.ViewModelAssistedFactory
import org.codepond.commitbrowser.model.CommitResponse
import timber.log.Timber

private const val STATE_RESPONSE = "state_response"
class CommitDetailViewModel @AssistedInject constructor(
    @Assisted handle: SavedStateHandle,
    githubApi: GithubApi
) : BaseViewModel(handle, githubApi) {
    val commitDetail: LiveData<CommitResponse>
        get() = _commitDetail
    private val _commitDetail = MutableLiveData<CommitResponse>()

    fun loadDetail(sha: String) {
        notifyLoading()
        Timber.v("Request commit detail for sha: %s", sha)
        val storedResponse: CommitResponse? = handle[STATE_RESPONSE]
        val storedSha = storedResponse?.sha
        if (sha == storedSha) {
            notifyLoaded()
            _commitDetail.value = storedResponse
        } else {
            viewModelScope.launch {
                val response = withContext(Dispatchers.IO) {
                    githubApi.getCommit(sha)
                }
                handle[STATE_RESPONSE] = response
                notifyLoaded()
                _commitDetail.value = response
            }
        }
    }

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<CommitDetailViewModel>
}
