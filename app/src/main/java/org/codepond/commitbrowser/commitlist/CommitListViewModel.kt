/*
 * Copyright 2019 Nimrod Dayan nimroddayan.com
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codepond.commitbrowser.commitlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.codepond.commitbrowser.api.GithubApi
import org.codepond.commitbrowser.common.ui.BaseViewModel
import org.codepond.commitbrowser.di.CoroutinesDispatcherProvider
import org.codepond.commitbrowser.di.ViewModelAssistedFactory
import timber.log.Timber

private const val STATE_PAGE = "state_page"

class CommitListViewModel @AssistedInject constructor(
    @Assisted handle: SavedStateHandle,
    githubApi: GithubApi,
    dispatchers: CoroutinesDispatcherProvider
) : BaseViewModel(handle, githubApi, dispatchers) {

    init {
        Timber.d("Initializing")
        val page = handle[STATE_PAGE] ?: 0
        viewModelScope.launch {
            val response = withContext(dispatchers.io) {
                githubApi.getCommits(page)
            }
            Timber.d("Data loaded for page %d", page)
            notifyLoaded(response.map {
                CommitInfo(
                    sha = it.sha,
                    avatar = it.author?.avatarUrl ?: "",
                    message = it.commit?.message ?: "",
                    date = it.commit?.author?.date ?: "",
                    author = it.author?.name ?: ""
                )
            })
        }
    }

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<CommitListViewModel>
}
