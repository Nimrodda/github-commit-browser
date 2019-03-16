package org.codepond.commitbrowser.commitlist;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.savedstate.SavedStateRegistryOwner;
import com.squareup.inject.assisted.dagger2.AssistedModule;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import org.codepond.commitbrowser.di.ViewModelAssistedFactory;
import org.codepond.commitbrowser.di.ViewModelKey;

@AssistedModule
@Module
public abstract class CommitListModule {
    @Binds
    @IntoMap
    @ViewModelKey(CommitListViewModel.class)
    abstract ViewModelAssistedFactory bindFactory(CommitListViewModel.Factory factory);

    @Binds
    abstract SavedStateRegistryOwner bindSavedStateRegistryOwner(CommitListActivity commitListActivity);

    @Nullable
    @Provides
    static Bundle provideDefaultArgs() {
        return null;
    }
}
