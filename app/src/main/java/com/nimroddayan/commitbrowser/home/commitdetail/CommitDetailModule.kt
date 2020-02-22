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
package com.nimroddayan.commitbrowser.home.commitdetail

import androidx.fragment.app.Fragment
import com.nimroddayan.commitbrowser.di.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CommitDetailModule {
    @Binds
    @IntoMap
    @FragmentKey(CommitDetailFragment::class)
    abstract fun bindCommitDetailFragment(fragment: CommitDetailFragment): Fragment
}
