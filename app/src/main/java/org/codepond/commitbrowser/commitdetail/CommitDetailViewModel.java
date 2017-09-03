package org.codepond.commitbrowser.commitdetail;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import org.codepond.commitbrowser.api.GithubApi;
import org.codepond.commitbrowser.commitlist.CommitItem;
import org.codepond.commitbrowser.common.recyclerview.Item;
import org.codepond.commitbrowser.common.ui.BaseViewModel;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class CommitDetailViewModel extends BaseViewModel {
    private ObservableList<Item> items = new ObservableArrayList<>();
    public CommitDetailViewModel(GithubApi githubApi) {
        super(githubApi);
    }

    public Observable<List<FileItem>> loadDetail(String sha) {
        Timber.v("Request commit list");
        isLoading.set(true);
        return getGithubApi().getCommit(sha)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(commitResponse -> {
                    items.add(new CommitItem(commitResponse));
                    if (commitResponse.files() != null) {
                        return Observable.from(commitResponse.files());
                    }
                    else {
                        return Observable.empty();
                    }
                }).flatMap(file -> Observable.just(new FileItem(file)))
                .toList()
                .doOnNext(fileItems -> {
                    items.addAll(fileItems);
                    isLoading.set(false);
                });
    }

    public ObservableList<Item> getItems() {
        return items;
    }
}
