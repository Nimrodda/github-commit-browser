package org.codepond.commitbrowser.commitdetail

import com.android.databinding.library.baseAdapters.BR

import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.common.recyclerview.Item
import org.codepond.commitbrowser.common.recyclerview.ViewHolder
import org.codepond.commitbrowser.model.File

data class FileItem(private val file: File) : Item() {
    override val layoutId: Int = R.layout.commit_file_list_item

    override fun bind(viewHolder: ViewHolder<*>) {
        viewHolder.binding.setVariable(BR.file, file)
        super.bind(viewHolder)
    }
}
