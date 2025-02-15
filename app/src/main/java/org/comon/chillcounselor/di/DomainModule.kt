package org.comon.chillcounselor.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.comon.chillcounselor.domain.repository.CounselRepository
import org.comon.chillcounselor.domain.usecase.RequestCounselUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideRequestCounselUseCase(counselRepository: CounselRepository): RequestCounselUseCase =
        RequestCounselUseCase(counselRepository)

}