package org.codepond.commitbrowser.home.commitdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.common.ui.BaseFragment
import org.codepond.commitbrowser.databinding.CommitDetailFragmentBinding

class CommitDetailFragment : BaseFragment<CommitDetailViewState, CommitDetailViewModel, CommitDetailFragmentBinding>() {
    override val viewModelClass: Class<CommitDetailViewModel> = CommitDetailViewModel::class.java
    override val layoutId: Int = R.layout.commit_detail_fragment

    private val args: CommitDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetail(args.sha)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = controller.adapter
        }

        return view
    }
}
