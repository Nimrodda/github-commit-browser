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

import com.nimroddayan.commitbrowser.api.GithubApi
import com.nimroddayan.commitbrowser.common.ui.Action
import com.nimroddayan.commitbrowser.common.ui.AsyncAction
import com.nimroddayan.commitbrowser.common.ui.ViewState
import javax.inject.Inject

sealed class FetchCommitListAction : Action {
    class Loading(val page: Int) : FetchCommitListAction()
    class Success(val commits: List<CommitInfo>) : FetchCommitListAction()
    class Failure(val throwable: Throwable) : FetchCommitListAction()
}

class FetchCommitListActionCreator @Inject constructor(
    private val githubApi: GithubApi
) {
    fun create(
        page: Int
    ): AsyncAction<ViewState<CommitListViewState>> {
        return { previousState, dispatch ->
            dispatch(FetchCommitListAction.Loading(page))
            try {
                val pages = (if (isRestoringSavedState(page, previousState)) 0 else page)..page
                dispatch(
                    FetchCommitListAction.Success(
                        pages.map { currentPage ->
                            githubApi.getCommits(currentPage).map {
                                CommitInfo(
                                    sha = it.sha,
                                    avatar = it.author?.avatarUrl ?: "",
                                    message = it.commit?.message ?: "",
                                    date = it.commit?.author?.date ?: "",
                                    author = it.author?.name ?: ""
                                )
                            }
                        }.flatten()
                    )
                )
            } catch (e: Exception) {
                dispatch(FetchCommitListAction.Failure(e))
            }
        }
    }


    private fun isRestoringSavedState(
        page: Int,
        previousState: ViewState<CommitListViewState>
    ) = page > 0 && previousState.data.list.isEmpty()
}
