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

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import org.codepond.commitbrowser.R;
import org.codepond.commitbrowser.commitdetail.CommitDetailActivity;
import org.codepond.commitbrowser.common.recyclerview.ItemAdapter;
import org.codepond.commitbrowser.common.recyclerview.OnItemClickListener;
import org.codepond.commitbrowser.common.recyclerview.OnLoadMoreScrollListener;
import org.codepond.commitbrowser.common.ui.NetworkErrorSnackBar;
import org.codepond.commitbrowser.databinding.CommitListActivityBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import rx.Subscription;
import timber.log.Timber;

public class CommitListActivity extends AppCompatActivity {
    private CommitListViewModel viewModel;
    private Subscription subscription;
    private CommitListActivityBinding binding;
    private NetworkErrorSnackBar networkErrorSnackBar;
    private OnItemClickListener onItemClickListener = id -> {
        Timber.v("Commit with sha: %s was clicked", id);
        Intent intent = new Intent(CommitListActivity.this, CommitDetailActivity.class);
        intent.putExtra(CommitDetailActivity.EXTRA_COMMIT_SHA, id);
        startActivity(intent);
    };

    @Inject CommitListViewModel.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CommitListViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.commit_list_activity);
        binding.setViewModel(viewModel);
        networkErrorSnackBar = new NetworkErrorSnackBar(binding.getRoot());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.commitList.setLayoutManager(layoutManager);
        binding.commitList.setItemAnimator(new DefaultItemAnimator());
        binding.commitList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.commitList.setAdapter(new ItemAdapter(viewModel.getCommits(), onItemClickListener));
        binding.commitList.addOnScrollListener(new OnLoadMoreScrollListener(getResources().getInteger(R.integer.load_threshold)) {
            @Override
            protected void onLoadMore() {
                loadMore();
            }
        });
    }

    private void loadMore() {
        subscription = viewModel.loadCommits()
                .retryWhen(networkErrorSnackBar::show)
                .subscribe();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (viewModel.getCommits().size() == 0) {
            loadMore();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
