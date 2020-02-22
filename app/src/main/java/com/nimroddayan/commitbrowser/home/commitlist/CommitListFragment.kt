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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nimroddayan.commitbrowser.R
import com.nimroddayan.commitbrowser.common.recyclerview.OnLoadMoreScrollListener
import com.nimroddayan.commitbrowser.common.ui.BaseFragment
import com.nimroddayan.commitbrowser.databinding.CommitListFragmentBinding
import timber.log.Timber

class CommitListFragment : BaseFragment<CommitListViewState, CommitListViewModel, CommitListFragmentBinding>() {
    override val viewModelClass: Class<CommitListViewModel> = CommitListViewModel::class.java
    override val layoutId: Int = R.layout.commit_list_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate()")
        viewModel.navigateToDetail.observe(this, Observer { sha ->
            Timber.d("Item with sha: %s was clicked", sha)
            findNavController().navigate(CommitListFragmentDirections.actionCommitListFragmentToCommitDetailFragment(sha))
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            addOnScrollListener(object : OnLoadMoreScrollListener(resources.getInteger(R.integer.load_threshold)) {
                override fun onLoadMore() {
                    controller.currentData?.let {
                        viewModel.loadData(it.data.page + 1)
                    }
                }
            })
            adapter = controller.adapter
        }

        savedInstanceState?.let { state ->
            val position = state.getInt("position")
            controller.adapter.addModelBuildListener {
                Timber.d("Restoring last recyclerview position: %d", position)
                binding.recyclerview.layoutManager?.scrollToPosition(position)
            }
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val position =
            (binding.recyclerview.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
        Timber.d("Saving current recyclerview position: %d", position)
        outState.putInt("position", position)
    }
}
