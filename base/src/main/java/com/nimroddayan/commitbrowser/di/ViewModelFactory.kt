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

package com.nimroddayan.commitbrowser.di

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

/**
 * Interface for constructing [ViewModel] that gets an instance of [SavedStateHandle]
 * as a parameter.
 *
 * Implement this interface passing all [ViewModel] dependencies to the implementation's
 * injectable constructor and return a new instance of the [ViewModel].
 *
 * Inject an instance of the implementation in a Fragment or Activity and pass it
 * to [withFactory] when using `by viewModels` like so:
 *
 *
 * ```
 * private val viewModel: CommitListViewModelNew by viewModels {
 *     withFactory(commitListViewModelFactory, this)
 * }
 * ```
 *
 * @see withFactory
 */
interface ViewModelFactory<out V : ViewModel> {
    fun create(handle: SavedStateHandle): V
}

class GenericSavedStateViewModelFactory<out V : ViewModel>(
    private val viewModelFactory: ViewModelFactory<V>,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return viewModelFactory.create(handle) as T
    }
}

/**
 * Convenience function to use with `by viewModels` that creates an instance of
 * [AbstractSavedStateViewModelFactory] that enables us to pass [SavedStateHandle]
 * to the [ViewModel]'s constructor.
 *
 * @param factory instance of [ViewModelFactory] that will be used to construct the [ViewModel]
 * @param owner instance of Fragment or Activity that owns the [ViewModel]
 * @param defaultArgs Bundle with default values to populate the [SavedStateHandle]
 *
 * @see ViewModelFactory
 */
@MainThread
inline fun <reified VM : ViewModel> SavedStateRegistryOwner.withFactory(
    factory: ViewModelFactory<VM>,
    defaultArgs: Bundle? = null
) = GenericSavedStateViewModelFactory(factory, this, defaultArgs)
