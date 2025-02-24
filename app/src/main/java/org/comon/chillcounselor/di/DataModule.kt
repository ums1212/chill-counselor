package org.comon.chillcounselor.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.comon.chillcounselor.data.api.CounchillorApi
import org.comon.chillcounselor.data.mapper.RequestCounselMapper
import org.comon.chillcounselor.data.mapper.ResponseCounselMapper
import org.comon.chillcounselor.data.repository.CounselRepositoryImpl
import org.comon.chillcounselor.data.util.NetworkStateManagerImpl
import org.comon.chillcounselor.domain.repository.CounselRepository
import org.comon.chillcounselor.domain.util.NetworkStateManager
import org.comon.data.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit (
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): CounchillorApi = retrofit.create(CounchillorApi::class.java)

    @Provides
    @Singleton
    fun provideRequestCounselMapper(): RequestCounselMapper = RequestCounselMapper()

    @Provides
    @Singleton
    fun provideResponseCounselMapper(): ResponseCounselMapper = ResponseCounselMapper()

    @Provides
    @Singleton
    fun provideCounselRepository(
        counchillorApi: CounchillorApi,
        requestCounselMapper: RequestCounselMapper,
        responseCounselMapper: ResponseCounselMapper,
    ): CounselRepository = CounselRepositoryImpl(counchillorApi, requestCounselMapper, responseCounselMapper)

    @Provides
    @Singleton
    fun provideNetworkStateManager(
        @ApplicationContext context: Context
    ): NetworkStateManager = NetworkStateManagerImpl(context)
}