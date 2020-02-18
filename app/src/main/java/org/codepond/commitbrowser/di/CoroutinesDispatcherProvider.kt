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

package org.codepond.commitbrowser.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject
import javax.inject.Singleton

interface CoroutinesDispatchers {
    val main: CoroutineDispatcher
    val computation: CoroutineDispatcher
    val io: CoroutineDispatcher
}

@Singleton
data class CoroutinesDispatcherProvider(
    override val main: CoroutineDispatcher,
    override val computation: CoroutineDispatcher,
    override val io: CoroutineDispatcher
) : CoroutinesDispatchers {
    @Inject
    constructor() : this(Main, Default, IO)
}
