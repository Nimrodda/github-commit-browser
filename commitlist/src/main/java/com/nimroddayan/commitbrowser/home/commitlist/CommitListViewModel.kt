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

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nimroddayan.commitbrowser.api.GithubApi
import com.nimroddayan.commitbrowser.common.network.InternetConnection
import com.nimroddayan.commitbrowser.common.ui.BaseViewModel
import com.nimroddayan.commitbrowser.common.ui.ReduxStore
import com.nimroddayan.commitbrowser.common.ui.Store
import com.nimroddayan.commitbrowser.common.ui.ViewState
import com.nimroddayan.commitbrowser.di.CoroutinesDispatcherProvider
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val STATE_PAGE = "state_page"

class CommitListViewModel(
    handle: SavedStateHandle,
    githubApi: GithubApi,
    dispatchers: CoroutinesDispatcherProvider,
    internetConnection: InternetConnection,
    private val store: Store<ViewState<CommitListViewState>>,
    private val fetchCommitListActionCreator: FetchCommitListActionCreator
) : BaseViewModel<CommitListViewState>(handle, githubApi, dispatchers, internetConnection),
    ReduxStore<ViewState<CommitListViewState>> by store {

    init {
        Timber.d("Initializing")
        val page = handle[STATE_PAGE] ?: 0
        loadCommits(page)
    }

    fun loadCommits(page: Int) {
        viewModelScope.launch {
            dispatch(fetchCommitListActionCreator.create(page))
        }
    }

    class Factory @Inject constructor(
        githubApi: GithubApi,
        dispatchers: CoroutinesDispatcherProvider,
        internetConnection: InternetConnection,
        private val store: Store<ViewState<CommitListViewState>>,
        private val fetchCommitListActionCreator: FetchCommitListActionCreator
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
                internetConnection,
                store,
                fetchCommitListActionCreator
            )
        }
    }
}
