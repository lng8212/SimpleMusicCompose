package com.longkd.simplemusiccompose.di

import android.content.Context
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.longkd.simplemusiccompose.data.db.MusicRemoteDatabase
import com.longkd.simplemusiccompose.data.repository.MusicRepositoryImpl
import com.longkd.simplemusiccompose.data.service.MusicControllerImpl
import com.longkd.simplemusiccompose.domain.repository.MusicRepository
import com.longkd.simplemusiccompose.domain.service.MusicController
import com.longkd.simplemusiccompose.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: longkd
 * @Since: 11:18 - 28/7/24
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCollection() = FirebaseFirestore.getInstance().collection(Constants.SONG_COLLECTION)

    @Singleton
    @Provides
    fun provideMusicDatabase(songCollection: CollectionReference) =
        MusicRemoteDatabase(songCollection)

    @Singleton
    @Provides
    fun provideMusicRepository(musicRemoteDatabase: MusicRemoteDatabase): MusicRepository =
        MusicRepositoryImpl(musicRemoteDatabase)

    @Singleton
    @Provides
    fun provideMusicController(@ApplicationContext context: Context): MusicController =
        MusicControllerImpl(context)
}
