package org.codepond.commitbrowser.di

import androidx.lifecycle.SavedStateHandle

interface ViewModelAssistedFactory<T> {
    fun create(handle: SavedStateHandle): T
}
