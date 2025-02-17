package org.comon.chillcounselor.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.comon.chillcounselor.data.repository.CounselRepositoryImpl
import org.comon.chillcounselor.domain.repository.CounselRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCounselRepository(impl: CounselRepositoryImpl): CounselRepository
}