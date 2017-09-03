package org.codepond.commitbrowser.commitdetail;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import org.codepond.commitbrowser.R;
import org.codepond.commitbrowser.commitlist.CommitListViewModel;
import org.codepond.commitbrowser.common.recyclerview.ItemAdapter;
import org.codepond.commitbrowser.common.ui.NetworkErrorSnackBar;
import org.codepond.commitbrowser.common.ui.ViewModelFactory;
import org.codepond.commitbrowser.databinding.CommitDetailActivityBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import rx.Subscription;

public class CommitDetailActivity extends AppCompatActivity {

    public static final String EXTRA_COMMIT_SHA = "extra_commit_sha";

    private Subscription subscription;
    private NetworkErrorSnackBar networkErrorSnackBar;
    private CommitDetailActivityBinding binding;
    private CommitDetailViewModel viewModel;
    private String sha;

    @Inject ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CommitDetailViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.commit_detail_activity);
        binding.setViewModel(viewModel);
        networkErrorSnackBar = new NetworkErrorSnackBar(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sha = getIntent().getStringExtra(EXTRA_COMMIT_SHA);
        setTitle(sha);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recyclerview.setAdapter(new ItemAdapter(viewModel.getItems(), null));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (viewModel.getItems().size() == 0) {
            subscription = viewModel.loadDetail(sha)
                    .retryWhen(networkErrorSnackBar::show)
                    .subscribe();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
