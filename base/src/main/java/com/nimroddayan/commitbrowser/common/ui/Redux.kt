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

package com.nimroddayan.commitbrowser.common.ui

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

interface Action

typealias AsyncAction<State> = suspend (previousState: State, dispatch: (action: Action) -> Unit) -> Unit

interface Reducer<State> {
    fun reduce(previousState: State, action: Action): State
}

interface ReduxStore<State> {
    val stateFlow: Flow<State>
    fun getState(): State
    fun dispatch(action: Action)
    suspend fun dispatch(action: AsyncAction<State>)
}

class Store<State>(
    initialState: State,
    private val reducer: Reducer<State>
) : ReduxStore<State> {
    private var state: State = initialState
    private val stateChannel = ConflatedBroadcastChannel<State>()

    override val stateFlow = stateChannel.asFlow()

    override fun getState(): State = state

    override fun dispatch(action: Action) {
        state = reducer.reduce(state, action)
        stateChannel.offer(state)
    }

    override suspend fun dispatch(action: AsyncAction<State>) {
        action(state, ::dispatch)
    }
}
