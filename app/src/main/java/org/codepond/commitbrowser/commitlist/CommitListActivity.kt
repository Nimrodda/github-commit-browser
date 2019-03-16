/*
 * Copyright 2019 Nimrod Dayan nimroddayan.com
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

package org.codepond.commitbrowser.commitlist

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.commitdetail.CommitDetailActivity
import org.codepond.commitbrowser.common.recyclerview.OnLoadMoreScrollListener
import org.codepond.commitbrowser.common.ui.BaseActivity
import org.codepond.commitbrowser.databinding.CommitListActivityBinding
import timber.log.Timber

class CommitListActivity : BaseActivity<CommitListViewModel, CommitListActivityBinding>() {
    override val viewModelClass: Class<CommitListViewModel> = CommitListViewModel::class.java
    override val layoutId: Int = R.layout.commit_list_activity

    private val onItemClickListener = { id: Int ->
        Timber.v("Commit with sha: %s was clicked", id)
        val intent = Intent(this@CommitListActivity, CommitDetailActivity::class.java)
        intent.putExtra(CommitDetailActivity.EXTRA_COMMIT_SHA, id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.itemAnimator = DefaultItemAnimator()
        binding.recyclerview.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerview.addOnScrollListener(object :
            OnLoadMoreScrollListener(resources.getInteger(R.integer.load_threshold)) {
            override fun onLoadMore() {
                loadMore()
            }
        })
    }

    private fun loadMore() {
    }
}
