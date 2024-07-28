package com.longkd.simplemusiccompose.domain.repository

import com.longkd.simplemusiccompose.domain.model.Song
import com.longkd.simplemusiccompose.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @Author: longkd
 * @Since: 11:22 - 28/7/24
 */

interface MusicRepository {
    fun getSong(): Flow<Resource<List<Song>>>
}