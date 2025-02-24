package org.comon.chillcounselor.player.bgmplayer

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import org.comon.chillcounselor.domain.bgmplayer.BGMPlayer
import org.comon.player.R
import javax.inject.Inject

class BGMPlayerImpl @Inject constructor(
    @ApplicationContext private val context: Context
): BGMPlayer {

    private val bgmUri by lazy {
        Uri.parse("android.resource://org.comon.chillcounselor/${R.raw.tonight}")
    }

    private val audioAttributes by lazy {
        AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()
    }

    private val mediaPlayer by lazy {
        MediaPlayer().apply {
            setAudioAttributes(audioAttributes)
            setDataSource(context, bgmUri)
            prepare()
        }
    }

    override fun play(){
        mediaPlayer.start()
    }

    override fun pause(){
        mediaPlayer.stop()
        mediaPlayer.prepare()
    }

}