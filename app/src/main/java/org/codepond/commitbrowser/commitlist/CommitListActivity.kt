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

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.common.recyclerview.OnLoadMoreScrollListener
import org.codepond.commitbrowser.common.ui.BaseActivity
import org.codepond.commitbrowser.common.ui.BaseViewModel
import org.codepond.commitbrowser.databinding.CommitListActivityBinding
import timber.log.Timber
import javax.inject.Inject

class CommitListActivity : BaseActivity<CommitListViewModel, CommitListActivityBinding>() {
    override val viewModelClass: Class<CommitListViewModel> = CommitListViewModel::class.java
    override val layoutId: Int = R.layout.commit_list_activity

    @Inject
    lateinit var controller: CommitListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@CommitListActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@CommitListActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            addOnScrollListener(object : OnLoadMoreScrollListener(resources.getInteger(R.integer.load_threshold)) {
                override fun onLoadMore() {
                    controller.currentData?.let {
                        viewModel.loadData(it.page + 1)
                    }
                }
            })
            adapter = controller.adapter
        }

        viewModel.loadingState.observe(this, Observer { state ->
            when (state) {
                is BaseViewModel.LoadingState.Loading -> {
                }
                is BaseViewModel.LoadingState.Error -> {
                }
                is BaseViewModel.LoadingState.Loaded<*> -> {
                    (state.data as? CommitListInfo)?.let {
                        Timber.d("Updating controller")
                        controller.setData(it)
                    }
                }
            }
        })
    }
}
