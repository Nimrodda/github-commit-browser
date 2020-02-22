/*
 * Copyright 2020 Nimrod Dayan nimroddayan.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nimroddayan.commitbrowser.home.commitlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nimroddayan.commitbrowser.api.GithubApi
import com.nimroddayan.commitbrowser.common.coroutines.withInternet
import com.nimroddayan.commitbrowser.common.network.InternetConnection
import com.nimroddayan.commitbrowser.common.ui.BaseViewModel
import com.nimroddayan.commitbrowser.di.CoroutinesDispatcherProvider
import com.nimroddayan.commitbrowser.model.CommitResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

private const val STATE_PAGE = "state_page"

class CommitListViewModel(
    handle: SavedStateHandle,
    githubApi: GithubApi,
    dispatchers: CoroutinesDispatcherProvider,
    internetConnection: InternetConnection
) : BaseViewModel<CommitListViewState>(handle, githubApi, dispatchers, internetConnection) {
    val navigateToDetail: LiveData<String>
        get() = _navigateToDetail
    private val _navigateToDetail = MutableLiveData<String>()

    private val commitList = mutableListOf<CommitInfo>()

    init {
        Timber.d("Initializing")
        val page = handle[STATE_PAGE] ?: 0
        loadData(page, page > 0)
    }

    fun loadData(page: Int, restoringState: Boolean = false) {
        handle[STATE_PAGE] = page
        notifyLoading(
            CommitListViewState(
                page = page,
                list = commitList
            )
        )
        viewModelScope.launch {
            val pages = (if (restoringState) 0 else page)..page
            val response = mutableListOf<CommitResponse>()
            pages.forEach { currentPage ->
                Timber.d("Fetching data for page: %d", currentPage)
                response += withContext(io) {
                    withInternet({ waitForInternetAndNotifyLoading(page) }, { reportErrorAndRetry(it, page) }) {
                        githubApi.getCommits(currentPage)
                    }
                }
            }
            Timber.d("Data loaded for page %d", page)
            notifyDataLoaded(
                CommitListViewState(
                    page = page,
                    list = commitList.apply {
                        addAll(response.map {
                            CommitInfo(
                                sha = it.sha,
                                avatar = it.author?.avatarUrl ?: "",
                                message = it.commit?.message ?: "",
                                date = it.commit?.author?.date ?: "",
                                author = it.author?.name ?: ""
                            )
                        })
                    },
                    onClick = _navigateToDetail::setValue
                )
            )
        }
    }

    private suspend fun waitForInternetAndNotifyLoading(page: Int) {
        waitForInternet()
        notifyLoading(CommitListViewState(page, commitList))
    }

    private fun reportErrorAndRetry(throwable: Throwable, page: Int): Boolean {
        notifyError(throwable, CommitListViewState(page = page, list = commitList))
        return retryAfterError(throwable)
    }

    class Factory @Inject constructor(
        githubApi: GithubApi,
        dispatchers: CoroutinesDispatcherProvider,
        internetConnection: InternetConnection
    ) : BaseViewModel.Factory<CommitListViewModel>(
        githubApi,
        dispatchers,
        internetConnection
    ) {
        override fun create(handle: SavedStateHandle): CommitListViewModel {
            return CommitListViewModel(
                handle,
                githubApi,
                dispatchers,
                internetConnection
            )
        }
    }
}
