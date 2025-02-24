package org.comon.chillcounselor.domain.usecase

import org.comon.chillcounselor.domain.bgmplayer.BGMPlayer

class ReleaseBGMUseCase(
    private val bgmPlayer: BGMPlayer
) {

    operator fun invoke(){
        bgmPlayer.release()
    }

}