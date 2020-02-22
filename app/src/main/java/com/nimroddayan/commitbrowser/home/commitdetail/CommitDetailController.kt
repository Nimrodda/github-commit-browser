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

package com.nimroddayan.commitbrowser.home.commitdetail

import com.nimroddayan.commitbrowser.base.loading
import com.nimroddayan.commitbrowser.commitFile
import com.nimroddayan.commitbrowser.common.epoxy.ViewStateEpoxyController
import com.nimroddayan.commitbrowser.common.ui.ViewState
import javax.inject.Inject

class CommitDetailController @Inject constructor() : ViewStateEpoxyController<CommitDetailViewState>() {
    override fun buildModels(state: ViewState<CommitDetailViewState>) {
        state.data.files?.forEach {
            commitFile {
                id("file")
                file(it)
            }
        }
        if (state.isLoading()) {
            loading {
                id("loading")
                matchParent(true)
            }
        }
    }
}
