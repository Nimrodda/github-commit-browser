package org.codepond.commitbrowser.home.commitdetail;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.savedstate.SavedStateRegistryOwner;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import org.codepond.commitbrowser.common.epoxy.ViewStateEpoxyController;
import org.codepond.commitbrowser.di.ViewModelAssistedFactory;
import org.codepond.commitbrowser.di.ViewModelKey;

@Module
public abstract class CommitDetailModule {
    @Binds
    @IntoMap
    @ViewModelKey(CommitDetailViewModel.class)
    abstract ViewModelAssistedFactory<? extends ViewModel> bindFactory(CommitDetailViewModel.Factory factory);

    @Binds
    abstract SavedStateRegistryOwner bindSavedStateRegistryOwner(CommitDetailFragment commitDetailFragment);

    @Nullable
    @Provides
    static Bundle provideDefaultArgs() {
        return null;
    }

    @Binds
    abstract ViewStateEpoxyController<CommitDetailViewState> bindViewStateEpoxyController(CommitDetailController controller);
}
