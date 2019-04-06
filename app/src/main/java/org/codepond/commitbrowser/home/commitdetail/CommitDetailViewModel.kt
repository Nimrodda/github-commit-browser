package org.codepond.commitbrowser.home.commitdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.codepond.commitbrowser.api.GithubApi
import org.codepond.commitbrowser.common.coroutines.withInternet
import org.codepond.commitbrowser.common.network.InternetConnection
import org.codepond.commitbrowser.common.ui.BaseViewModel
import org.codepond.commitbrowser.di.CoroutinesDispatcherProvider
import org.codepond.commitbrowser.di.ViewModelAssistedFactory
import org.codepond.commitbrowser.model.CommitResponse
import timber.log.Timber

private const val STATE_RESPONSE = "state_response"

class CommitDetailViewModel @AssistedInject constructor(
    @Assisted handle: SavedStateHandle,
    githubApi: GithubApi,
    dispatchers: CoroutinesDispatcherProvider,
    private val internetConnection: InternetConnection
) : BaseViewModel(handle, githubApi, dispatchers) {

    fun loadDetail(sha: String) {
        notifyStateChanged(
            CommitDetailViewState(
                loading = true
            )
        )
        Timber.d("Request commit detail for sha: %s", sha)
        val storedResponse: CommitResponse? = handle[STATE_RESPONSE]
        val storedSha = storedResponse?.sha
        if (sha == storedSha) {
            Timber.d("Loaded from SavedState")
            notifyStateChanged(
                CommitDetailViewState(
                    loading = false,
                    files = storedResponse.files
                )
            )
        } else {
            viewModelScope.launch {
                val response = withContext(dispatchers.io) {
                    withInternet(internetConnection::waitForInternet, ::retryAfterError) {
                        githubApi.getCommit(sha)
                    }
                }
                Timber.d("Saving to SavedState")
                handle[STATE_RESPONSE] = response
                notifyStateChanged(
                    CommitDetailViewState(
                        loading = false,
                        files = response.files
                    )
                )
            }
        }
    }

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<CommitDetailViewModel>
}

