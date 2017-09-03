package org.codepond.commitbrowser.commitdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.codepond.commitbrowser.R;

public class CommitDetailActivity extends AppCompatActivity {

    public static final String EXTRA_COMMIT_SHA = "extra_commit_sha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commit_detail_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String sha = getIntent().getStringExtra(EXTRA_COMMIT_SHA);
    }
}
