package org.comon.chillcounselor.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.comon.chillcounselor.domain.bgmplayer.BGMPlayer
import org.comon.chillcounselor.domain.repository.CounselRepository
import org.comon.chillcounselor.domain.usecase.CheckNetworkStateUseCase
import org.comon.chillcounselor.domain.usecase.PauseBGMUseCase
import org.comon.chillcounselor.domain.usecase.PlayBGMUseCase
import org.comon.chillcounselor.domain.usecase.ReleaseBGMUseCase
import org.comon.chillcounselor.domain.usecase.RequestCounselUseCase
import org.comon.chillcounselor.domain.util.NetworkStateManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideRequestCounselUseCase(counselRepository: CounselRepository): RequestCounselUseCase =
        RequestCounselUseCase(counselRepository)

    @Provides
    @Singleton
    fun  provideCheckNetworkStateUseCase(networkStateManager: NetworkStateManager): CheckNetworkStateUseCase =
        CheckNetworkStateUseCase(networkStateManager)

    @Provides
    @Singleton
    fun  providePlayBGMUseCase(bgmPlayer: BGMPlayer): PlayBGMUseCase =
        PlayBGMUseCase(bgmPlayer)

    @Provides
    @Singleton
    fun  providePauseBGMUseCase(bgmPlayer: BGMPlayer): PauseBGMUseCase =
        PauseBGMUseCase(bgmPlayer)

    @Provides
    @Singleton
    fun  provideReleaseBGMUseCase(bgmPlayer: BGMPlayer): ReleaseBGMUseCase =
        ReleaseBGMUseCase(bgmPlayer)

}