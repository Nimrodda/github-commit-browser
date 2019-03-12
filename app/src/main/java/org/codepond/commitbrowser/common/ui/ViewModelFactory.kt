package org.codepond.commitbrowser.common.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import org.codepond.commitbrowser.api.GithubApi
import org.codepond.commitbrowser.commitdetail.CommitDetailViewModel
import org.codepond.commitbrowser.commitlist.CommitListViewModel

import javax.inject.Inject

class ViewModelFactory @Inject
constructor(private val githubApi: GithubApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommitDetailViewModel::class.java)) {
            return CommitDetailViewModel(githubApi) as T
        } else if (modelClass.isAssignableFrom(CommitListViewModel::class.java)) {
            return CommitListViewModel(githubApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
