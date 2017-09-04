package org.codepond.commitbrowser.common.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.databinding.library.baseAdapters.BR;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import rx.Observable;
import rx.Subscription;

public abstract class BaseActivity<T extends ViewModel, R extends ViewDataBinding> extends AppCompatActivity {
    private T viewModel;
    private Subscription subscription;
    private R binding;
    private NetworkErrorSnackBar networkErrorSnackBar;
    @Inject ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass());
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        binding.setVariable(BR.viewModel, viewModel);
        networkErrorSnackBar = new NetworkErrorSnackBar(binding.getRoot());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    protected abstract Class<T> getViewModelClass();

    @LayoutRes protected abstract int getLayoutId();

    protected <V extends Observable<?>> void subscribe(V observable) {
        subscription = observable
                .retryWhen(networkErrorSnackBar::show)
                .subscribe();
    }

    public T getViewModel() {
        return viewModel;
    }

    public R getBinding() {
        return binding;
    }
}
