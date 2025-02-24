package org.comon.chillcounselor.player.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.comon.chillcounselor.domain.bgmplayer.BGMPlayer
import org.comon.chillcounselor.player.bgmplayer.BGMPlayerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BGMPlayerModule {

    @Binds
    @Singleton
    abstract fun bindBGMPlayer(impl: BGMPlayerImpl): BGMPlayer
}