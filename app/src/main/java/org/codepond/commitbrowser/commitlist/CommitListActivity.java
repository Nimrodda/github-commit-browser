/*
 * Copyright 2017 Nimrod Dayan CodePond.org
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

package org.codepond.commitbrowser.commitlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import org.codepond.commitbrowser.R;
import org.codepond.commitbrowser.commitdetail.CommitDetailActivity;
import org.codepond.commitbrowser.common.recyclerview.ItemAdapter;
import org.codepond.commitbrowser.common.recyclerview.OnItemClickListener;
import org.codepond.commitbrowser.common.recyclerview.OnLoadMoreScrollListener;
import org.codepond.commitbrowser.common.ui.BaseActivity;
import org.codepond.commitbrowser.common.ui.ViewModelFactory;
import org.codepond.commitbrowser.databinding.CommitListActivityBinding;

import javax.inject.Inject;

import timber.log.Timber;

public class CommitListActivity extends BaseActivity<CommitListViewModel, CommitListActivityBinding> {
    @Inject ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        getBinding().recyclerview.setLayoutManager(layoutManager);
        getBinding().recyclerview.setItemAnimator(new DefaultItemAnimator());
        getBinding().recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        getBinding().recyclerview.setAdapter(new ItemAdapter(getViewModel().getCommits(), onItemClickListener));
        getBinding().recyclerview.addOnScrollListener(new OnLoadMoreScrollListener(getResources().getInteger(R.integer.load_threshold)) {
            @Override
            protected void onLoadMore() {
                loadMore();
            }
        });
    }

    private OnItemClickListener onItemClickListener = id -> {
        Timber.v("Commit with sha: %s was clicked", id);
        Intent intent = new Intent(CommitListActivity.this, CommitDetailActivity.class);
        intent.putExtra(CommitDetailActivity.EXTRA_COMMIT_SHA, id);
        startActivity(intent);
    };

    @Override
    protected Class<CommitListViewModel> getViewModelClass() {
        return CommitListViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.commit_list_activity;
    }

    private void loadMore() {
        subscribe(getViewModel().loadCommits());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getViewModel().getCommits().size() == 0) {
            loadMore();
        }
    }
}
