package com.longkd.simplemusiccompose.data.db

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot

/**
 * @Author: longkd
 * @Since: 11:19 - 28/7/24
 */
class MusicRemoteDatabase(private val songCollection: CollectionReference) {
    fun getAllSong(): Task<QuerySnapshot> = songCollection.get()
}