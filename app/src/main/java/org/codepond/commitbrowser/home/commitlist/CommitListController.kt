package org.codepond.commitbrowser.home.commitlist

import org.codepond.commitbrowser.commitInfo
import org.codepond.commitbrowser.common.epoxy.ViewStateEpoxyController
import org.codepond.commitbrowser.common.ui.ViewState
import org.codepond.commitbrowser.loading
import javax.inject.Inject

class CommitListController @Inject constructor(
) : ViewStateEpoxyController<CommitListViewState>() {
    override fun buildModels(state: ViewState<CommitListViewState>) {
        state.data.list.forEach {
            commitInfo {
                id(it.sha)
                commitInfo(it)
                clickListener { model, _, _, _ ->
                    state.data.onClick?.invoke(model.commitInfo().sha)
                }
            }
        }
        if (state.isLoading()) {
            loading {
                id("loading")
                matchParent(state.data.list.isEmpty())
            }
        }
    }
}
