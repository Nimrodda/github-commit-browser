package org.codepond.commitbrowser.common.ui;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

public abstract class BaseViewModel extends ViewModel {
    public final ObservableBoolean isLoading = new ObservableBoolean();
}
