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

package org.codepond.commitbrowser.common.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

abstract class OnLoadMoreScrollListener(
    private val threshold: Int
) : RecyclerView.OnScrollListener() {
    private var loading = false
    private var totalCountBeforeLoad: Int = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val visibleCount = layoutManager.childCount
        val totalCount = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        Timber.v("visibleCount = %d", visibleCount)
        Timber.v("totalCount = %d", totalCount)
        Timber.v("lastVisibleItemPosition = %d", lastVisibleItemPosition)

        if (!loading && lastVisibleItemPosition == totalCount - 1 - threshold) {
            loading = true
            totalCountBeforeLoad = totalCount
            Timber.v("Loading more...")
            onLoadMore()
        } else if (loading && totalCount > totalCountBeforeLoad) {
            Timber.v("Finished loading")
            loading = false
        }
    }

    protected abstract fun onLoadMore()
}
