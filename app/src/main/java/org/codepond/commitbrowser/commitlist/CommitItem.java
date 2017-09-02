/*
 * Copyright 2017 Nimrod Dayan CodePond.org
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codepond.commitbrowser.commitlist;

import com.android.databinding.library.baseAdapters.BR;

import org.codepond.commitbrowser.R;
import org.codepond.commitbrowser.common.recyclerview.Item;
import org.codepond.commitbrowser.common.recyclerview.ViewHolder;
import org.codepond.commitbrowser.model.CommitResponse;

public class CommitItem extends Item {
    private final CommitListViewModel commitListViewModel;
    private final String sha;
    private final String message;
    private final String date;
    private final String author;

    public CommitItem(CommitResponse commitResponse,
                      CommitListViewModel commitListViewModel) {
        this.commitListViewModel = commitListViewModel;
        this.sha = commitResponse.sha();
        this.message = commitResponse.commit().message();
        this.date = commitResponse.commit().author().date();
        this.author = commitResponse.commit().author().name();
    }

    @Override
    public void bind(ViewHolder viewHolder) {
        viewHolder.binding.setVariable(BR.commitListViewModel, commitListViewModel);
        viewHolder.binding.setVariable(BR.commitItem, this);
        super.bind(viewHolder);
    }

    @Override
    public int getLayoutId() {
        return R.layout.commit_item;
    }

    public String getSha() {
        return sha;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }
}
