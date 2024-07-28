package com.longkd.simplemusiccompose.data.repository

import com.google.firebase.firestore.toObjects
import com.longkd.simplemusiccompose.data.db.MusicRemoteDatabase
import com.longkd.simplemusiccompose.data.db.model.SongDto
import com.longkd.simplemusiccompose.data.mapper.toSong
import com.longkd.simplemusiccompose.domain.model.Song
import com.longkd.simplemusiccompose.domain.repository.MusicRepository
import com.longkd.simplemusiccompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

/**
 * @Author: longkd
 * @Since: 11:32 - 28/7/24
 */

class MusicRepositoryImpl(private val musicRemoteDatabase: MusicRemoteDatabase) : MusicRepository {
    override fun getSong(): Flow<Resource<List<Song>>> {
        return flow {
            val songs = musicRemoteDatabase.getAllSong().await().toObjects<SongDto>()
            if (songs.isNotEmpty()) emit(Resource.Success(songs.map { it.toSong() }))
            else emit(Resource.Error(message = "Empty"))
        }
    }
}