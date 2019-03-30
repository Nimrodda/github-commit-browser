package org.codepond.commitbrowser.home.commitlist;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.savedstate.SavedStateRegistryOwner;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import org.codepond.commitbrowser.di.ViewModelAssistedFactory;
import org.codepond.commitbrowser.di.ViewModelKey;

@Module
public abstract class CommitListModule {
    @Binds
    @IntoMap
    @ViewModelKey(CommitListViewModel.class)
    abstract ViewModelAssistedFactory<? extends ViewModel> bindFactory(CommitListViewModel.Factory factory);

    @Binds
    abstract SavedStateRegistryOwner bindSavedStateRegistryOwner(CommitListFragment commitListFragment);

    @Nullable
    @Provides
    static Bundle provideDefaultArgs() {
        return null;
    }
}
