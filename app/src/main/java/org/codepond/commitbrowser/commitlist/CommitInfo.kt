package org.codepond.commitbrowser.commitlist

data class CommitInfo(
    val sha: String,
    val avatar: String,
    val message: String,
    val date: String,
    val author: String
)
