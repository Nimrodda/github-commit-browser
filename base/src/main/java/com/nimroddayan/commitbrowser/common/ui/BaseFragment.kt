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

package com.nimroddayan.commitbrowser.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.nimroddayan.commitbrowser.base.R
import com.nimroddayan.commitbrowser.common.epoxy.ViewStateEpoxyController
import timber.log.Timber

abstract class BaseFragment<S, T : BaseViewModel<S>, B : ViewDataBinding>(
    protected val controller: ViewStateEpoxyController<S>,
    @LayoutRes private val layoutId: Int
) : Fragment() {
    protected lateinit var binding: B
    protected abstract val viewModel: T

    private var errorSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewState.observe(this, Observer { state ->
            Timber.d("Updating controller")
            controller.setData(state)

            lifecycleScope.launchWhenStarted {
                if (state is ViewState.Error) {
                    showError(state.throwable)
                } else {
                    dismissErrorIfShown()
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    private fun showError(error: Throwable) {
        errorSnackBar = Snackbar.make(binding.root, R.string.no_internet_connection, Snackbar.LENGTH_INDEFINITE).apply {
            show()
        }
    }

    private fun dismissErrorIfShown() {
        errorSnackBar?.dismiss()
    }
}
