package org.codepond.commitbrowser.commitdetail

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.common.ui.BaseActivity
import org.codepond.commitbrowser.databinding.CommitDetailActivityBinding

class CommitDetailActivity : BaseActivity<CommitDetailViewModel, CommitDetailActivityBinding>() {
    override val viewModelClass: Class<CommitDetailViewModel> = CommitDetailViewModel::class.java
    override val layoutId: Int = R.layout.commit_detail_activity

    private lateinit var sha: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        sha = intent.getStringExtra(EXTRA_COMMIT_SHA)
        title = sha

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.itemAnimator = DefaultItemAnimator()
        binding.recyclerview.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.commitDetail.observe(this, Observer {
            // Update recyclerview adapter
        })
        viewModel.loadDetail(sha)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_COMMIT_SHA = "extra_commit_sha"
    }
}
