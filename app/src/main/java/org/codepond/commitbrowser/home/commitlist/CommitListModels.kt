package org.codepond.commitbrowser.home.commitlist

data class CommitListInfo(
    val loading: Boolean,
    val page: Int,
    val list: List<CommitInfo>,
    val onClick: ((sha: String) -> Unit)? = null
)

data class CommitInfo(
    val sha: String,
    val avatar: String,
    val message: String,
    val date: String,
    val author: String
)
