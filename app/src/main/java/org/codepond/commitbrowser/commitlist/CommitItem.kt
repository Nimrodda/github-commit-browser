/*
 * Copyright 2017 Nimrod Dayan CodePond.org
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

import com.android.databinding.library.baseAdapters.BR

import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.common.recyclerview.Item
import org.codepond.commitbrowser.common.recyclerview.OnItemClickListener
import org.codepond.commitbrowser.common.recyclerview.ViewHolder
import org.codepond.commitbrowser.model.CommitResponse

class CommitItem(commitResponse: CommitResponse) : Item() {
    override val layoutId: Int = R.layout.commit_list_item

    val sha: String? = commitResponse.sha
    val message: String? = commitResponse.commit?.message
    val date: String? = commitResponse.commit?.author?.date
    val author: String? = commitResponse.commit?.author?.name
    val avatarUrl: String?

    init {
        if (commitResponse.author?.avatarUrl != null) {
            this.avatarUrl = commitResponse.author.avatarUrl
        } else {
            this.avatarUrl = null
        }
    }

    override fun bind(viewHolder: ViewHolder<*>, onItemClickListener: OnItemClickListener) {
        viewHolder.binding.setVariable(BR.commitItem, this)
        super.bind(viewHolder, onItemClickListener)
    }
}
