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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nimroddayan.commitbrowser.common.ui.BaseFragment
import com.nimroddayan.commitbrowser.home.commitdetail.databinding.CommitDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CommitDetailFragment :
    BaseFragment<CommitDetailViewState, CommitDetailViewModel, CommitDetailFragmentBinding>() {

    @Inject
    lateinit var commitDetailController: CommitDetailController

    override val layoutId: Int = R.layout.commit_detail_fragment

    override val viewModel: CommitDetailViewModel by viewModels()

    private val args: CommitDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        controller = commitDetailController
        super.onCreate(savedInstanceState)
        viewModel.loadDetail(args.sha)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = controller.adapter
        }

        return view
    }
}
