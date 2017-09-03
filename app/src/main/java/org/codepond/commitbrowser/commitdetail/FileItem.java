package org.codepond.commitbrowser.commitdetail;

import com.android.databinding.library.baseAdapters.BR;

import org.codepond.commitbrowser.R;
import org.codepond.commitbrowser.common.recyclerview.Item;
import org.codepond.commitbrowser.common.recyclerview.ViewHolder;
import org.codepond.commitbrowser.model.File;

public class FileItem extends Item {
    private File file;

    public FileItem(File file) {
        this.file = file;
    }

    @Override
    public int getLayoutId() {
        return R.layout.commit_file_list_item;
    }

    @Override
    public void bind(ViewHolder viewHolder) {
        viewHolder.binding.setVariable(BR.file, file);
        super.bind(viewHolder);
    }
}
