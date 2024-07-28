package com.longkd.simplemusiccompose.domain.service

import com.longkd.simplemusiccompose.domain.model.Song
import com.longkd.simplemusiccompose.util.PlayerState

/**
 * @Author: longkd
 * @Since: 23:31 - 28/7/24
 */
interface MusicController {
    var mediaControllerCallback: ((
        playerState: PlayerState,
        currentMusic: Song?,
        currentPosition: Long,
        totalDuration: Long,
        isShuffleEnabled: Boolean,
        iRepeatOneEnabled: Boolean,
    ) -> Unit)?

    fun addMediaItems(songs: List<Song>)
    fun play(mediaItemIndex: Int)
    fun resume()
    fun pause()
    fun getCurrentPosition(): Long
    fun destroy()
    fun skipToNextSong()
    fun skipToPreviousSong()
    fun getCurrentSong(): Song?
    fun seekTo(position: Long)
}