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

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import org.codepond.commitbrowser.api.GithubApi;

import javax.inject.Inject;

public class CommitListViewModel extends ViewModel {
    private GithubApi githubApi;
    private ObservableList<CommitItemViewModel> commits = new ObservableArrayList<>();

    @Inject public CommitListViewModel(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    public ObservableList<CommitItemViewModel> getCommits() {
        return commits;
    }
}
