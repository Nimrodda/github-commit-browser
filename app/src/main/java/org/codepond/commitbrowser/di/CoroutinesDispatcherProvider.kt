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
