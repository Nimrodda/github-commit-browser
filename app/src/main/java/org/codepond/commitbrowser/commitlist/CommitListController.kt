package org.codepond.commitbrowser.commitlist

import com.airbnb.epoxy.TypedEpoxyController
import org.codepond.commitbrowser.commitInfo
import javax.inject.Inject

class CommitListController @Inject constructor(
) : TypedEpoxyController<List<CommitInfo>>() {
    override fun buildModels(data: List<CommitInfo>) {
        data.forEach {
            commitInfo {
                id(it.sha)
                commitInfo(it)
            }
        }
    }
}
