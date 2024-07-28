package com.longkd.simplemusiccompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * @Author: longkd
 * @Since: 22:29 - 28/7/24
 */


@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val niaDispatcher: MusicDispatcher)

enum class MusicDispatcher {
    Default,
    IO,
}


@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(MusicDispatcher.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(MusicDispatcher.Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
