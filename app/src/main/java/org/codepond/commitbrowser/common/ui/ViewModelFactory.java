package org.codepond.commitbrowser.common.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import org.codepond.commitbrowser.api.GithubApi;
import org.codepond.commitbrowser.commitdetail.CommitDetailViewModel;
import org.codepond.commitbrowser.commitlist.CommitListViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private GithubApi githubApi;

    @Inject
    public ViewModelFactory(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CommitDetailViewModel.class)) {
            return (T) new CommitDetailViewModel(githubApi);
        }
        else if (modelClass.isAssignableFrom(CommitListViewModel.class)) {
            return (T) new CommitListViewModel(githubApi);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
