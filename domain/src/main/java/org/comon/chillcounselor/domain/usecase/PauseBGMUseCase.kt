package org.comon.chillcounselor.domain.usecase

import org.comon.chillcounselor.domain.bgmplayer.BGMPlayer

class PauseBGMUseCase(
    private val bgmPlayer: BGMPlayer
) {

    operator fun invoke(){
        bgmPlayer.pause()
    }

}