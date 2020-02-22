/*
 * Copyright 2020 Nimrod Dayan nimroddayan.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nimroddayan.commitbrowser.home.commitdetail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.savedstate.SavedStateRegistryOwner;

import com.nimroddayan.commitbrowser.common.epoxy.ViewStateEpoxyController;
import com.nimroddayan.commitbrowser.di.ViewModelAssistedFactory;
import com.nimroddayan.commitbrowser.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

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
