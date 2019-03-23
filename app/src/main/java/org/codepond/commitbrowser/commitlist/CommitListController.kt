package org.codepond.commitbrowser.commitlist

import com.airbnb.epoxy.TypedEpoxyController
import org.codepond.commitbrowser.commitInfo
import javax.inject.Inject

class CommitListController @Inject constructor(
) : TypedEpoxyController<CommitListInfo>() {
    override fun buildModels(data: CommitListInfo) {
        data.list.forEach {
            commitInfo {
                id(it.sha)
                commitInfo(it)
            }
        }
    }
}
