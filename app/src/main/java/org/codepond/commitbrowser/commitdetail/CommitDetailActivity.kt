package org.codepond.commitbrowser.commitdetail

import android.os.Bundle
import android.view.MenuItem
import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.common.ui.BaseActivity
import org.codepond.commitbrowser.databinding.CommitDetailActivityBinding

class CommitDetailActivity : BaseActivity<CommitDetailViewModel, CommitDetailActivityBinding>() {
    override val viewModelClass: Class<CommitDetailViewModel> = CommitDetailViewModel::class.java
    override val layoutId: Int = R.layout.commit_detail_activity

    private lateinit var sha: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        sha = intent.getStringExtra(EXTRA_COMMIT_SHA)
        title = sha

        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        binding.recyclerview.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                this,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
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
