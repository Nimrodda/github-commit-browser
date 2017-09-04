package org.codepond.commitbrowser.commitdetail;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import org.codepond.commitbrowser.R;
import org.codepond.commitbrowser.common.recyclerview.ItemAdapter;
import org.codepond.commitbrowser.common.ui.BaseActivity;
import org.codepond.commitbrowser.databinding.CommitDetailActivityBinding;

public class CommitDetailActivity extends BaseActivity<CommitDetailViewModel, CommitDetailActivityBinding> {
    public static final String EXTRA_COMMIT_SHA = "extra_commit_sha";

    private String sha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sha = getIntent().getStringExtra(EXTRA_COMMIT_SHA);
        setTitle(sha);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        getBinding().recyclerview.setLayoutManager(layoutManager);
        getBinding().recyclerview.setItemAnimator(new DefaultItemAnimator());
        getBinding().recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        getBinding().recyclerview.setAdapter(new ItemAdapter(getViewModel().getItems(), null));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getViewModel().getItems().size() == 0) {
            subscribe(getViewModel().loadDetail(sha));
        }
    }

    @Override
    protected Class<CommitDetailViewModel> getViewModelClass() {
        return CommitDetailViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.commit_detail_activity;
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
