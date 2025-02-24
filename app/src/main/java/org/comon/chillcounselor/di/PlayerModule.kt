package org.comon.chillcounselor.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.comon.chillcounselor.domain.bgmplayer.BGMPlayer
import org.comon.chillcounselor.player.bgmplayer.BGMPlayerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @Provides
    @Singleton
    fun provideBGMPlayer(
        @ApplicationContext context: Context
    ): BGMPlayer = BGMPlayerImpl(context)

}