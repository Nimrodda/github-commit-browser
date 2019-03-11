package org.codepond.commitbrowser.commitdetail

import org.codepond.commitbrowser.api.GithubApi
import org.codepond.commitbrowser.common.ui.BaseViewModel
import timber.log.Timber

class CommitDetailViewModel(githubApi: GithubApi) : BaseViewModel(githubApi) {
    fun loadDetail(sha: String) {
        Timber.v("Request commit list")
    }
}
