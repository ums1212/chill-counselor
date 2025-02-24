package org.comon.chillcounselor.domain.usecase

import org.comon.chillcounselor.domain.bgmplayer.BGMPlayer

class PlayBGMUseCase(
    private val bgmPlayer: BGMPlayer
) {

    operator fun invoke(){
        bgmPlayer.play()
    }

}