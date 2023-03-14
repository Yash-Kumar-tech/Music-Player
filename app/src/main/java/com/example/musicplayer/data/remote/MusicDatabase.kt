package com.example.musicplayer.data.remote

import com.example.musicplayer.data.entities.Song
import com.example.musicplayer.util.Constants.SONG_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MusicDatabase {

    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONG_COLLECTION)

    suspend fun getAllSongs(): List<Song> {
        return try {
            songCollection.get().await().toObjects(Song::class.java).sortedBy {
                it.title
            }
        } catch(e: Exception) {
            emptyList()
        }
    }
}