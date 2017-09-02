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

package org.codepond.commitbrowser.common.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import timber.log.Timber;

public abstract class OnLoadMoreScrollListener extends RecyclerView.OnScrollListener {
    private boolean loading = false;
    private int totalCountBeforeLoad;
    private int threshold;

    public OnLoadMoreScrollListener() {
        this(0);
    }

    public OnLoadMoreScrollListener(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        final int visibleCount = layoutManager.getChildCount();
        final int totalCount = layoutManager.getItemCount();
        final int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        Timber.v("visibleCount = %d", visibleCount);
        Timber.v("totalCount = %d", totalCount);
        Timber.v("lastVisibleItemPosition = %d", lastVisibleItemPosition);

        if (!loading && lastVisibleItemPosition == totalCount - 1 - threshold) {
            loading = true;
            totalCountBeforeLoad = totalCount;
            Timber.v("Loading more...");
            onLoadMore();
        }
        else if (loading && totalCount > totalCountBeforeLoad) {
            Timber.v("Finished loading");
            loading = false;
        }
    }

    protected abstract void onLoadMore();
}
