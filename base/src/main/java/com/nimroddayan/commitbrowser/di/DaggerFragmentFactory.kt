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

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

/**
 * Factory that instantiates fragments using Dagger
 */
class DaggerFragmentFactory
@Inject constructor(
    private val providers: MutableMap<Class<out @JvmSuppressWildcards Fragment>,
        @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return try {
            val fragmentClass = loadFragmentClass(classLoader, className)
            providers[fragmentClass]?.get()
                ?: throw IllegalStateException("No provider found for $className. " +
                    "Falling back to default factory.")
        } catch (e: Exception) {
            super.instantiate(classLoader, className)
        }
    }
}
