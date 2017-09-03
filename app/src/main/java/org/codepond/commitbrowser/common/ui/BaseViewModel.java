package org.codepond.commitbrowser.common.ui;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import org.codepond.commitbrowser.api.GithubApi;

public abstract class BaseViewModel extends ViewModel {
    public final ObservableBoolean isLoading = new ObservableBoolean();
    private final GithubApi githubApi;

    public BaseViewModel(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    protected GithubApi getGithubApi() {
        return githubApi;
    }
}
