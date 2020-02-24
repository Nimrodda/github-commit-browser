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

package com.nimroddayan.commitbrowser.home

import androidx.navigation.NavController
import com.nimroddayan.commitbrowser.common.navigation.DefaultNavigator
import com.nimroddayan.commitbrowser.common.navigation.NavigationRequest
import com.nimroddayan.commitbrowser.di.ActivityScope
import com.nimroddayan.commitbrowser.home.commitlist.CommitInfo
import com.nimroddayan.commitbrowser.home.commitlist.CommitListFragmentDirections
import com.nimroddayan.commitbrowser.home.commitlist.CommitListNavigation
import javax.inject.Inject

@ActivityScope
class HomeNavigator @Inject constructor() : DefaultNavigator(), CommitListNavigation {
    override suspend fun navigateToCommitDetails(commitInfo: CommitInfo) {
        navigate(CommitDetailNavRequest(commitInfo))
    }
}

class CommitDetailNavRequest(private val commitInfo: CommitInfo) : NavigationRequest {
    override fun navigate(navController: NavController) {
        navController.navigate(
            CommitListFragmentDirections.actionCommitListFragmentToCommitDetailFragment(
                commitInfo.sha,
                commitInfo.message
            )
        )
    }
}
