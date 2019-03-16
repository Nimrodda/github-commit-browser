package org.codepond.commitbrowser.commitdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import org.codepond.commitbrowser.api.GithubApi
import org.codepond.commitbrowser.common.ui.BaseViewModel
import org.codepond.commitbrowser.di.ViewModelAssistedFactory
import timber.log.Timber

class CommitDetailViewModel @AssistedInject constructor(
    @Assisted handle: SavedStateHandle,
    githubApi: GithubApi
) : BaseViewModel(handle, githubApi) {
    fun loadDetail(sha: String) {
        Timber.v("Request commit detail for sha: %s", sha)
        viewModelScope.launch {
            githubApi.getCommit(sha)
        }
    }

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<CommitDetailViewModel>
}
