package org.codepond.commitbrowser.commitdetail

import androidx.lifecycle.SavedStateHandle
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import org.codepond.commitbrowser.api.GithubApi
import org.codepond.commitbrowser.common.ui.BaseViewModel
import org.codepond.commitbrowser.di.ViewModelAssistedFactory
import timber.log.Timber

class CommitDetailViewModel @AssistedInject constructor(
    @Assisted handle: SavedStateHandle,
    githubApi: GithubApi
) : BaseViewModel(handle, githubApi) {
    fun loadDetail(sha: String) {
        Timber.v("Request commit list")
    }

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<CommitDetailViewModel>
}
