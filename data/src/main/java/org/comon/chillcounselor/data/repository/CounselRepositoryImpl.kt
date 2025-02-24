package org.comon.chillcounselor.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okio.IOException
import org.comon.chillcounselor.data.api.CounchillorApi
import org.comon.chillcounselor.data.mapper.RequestCounselMapper
import org.comon.chillcounselor.data.mapper.ResponseCounselMapper
import org.comon.chillcounselor.domain.model.RequestCounselData
import org.comon.chillcounselor.domain.model.ResponseCounselData
import org.comon.chillcounselor.domain.repository.CounselRepository
import javax.inject.Inject

class CounselRepositoryImpl @Inject constructor(
    private val counchillorApi: CounchillorApi,
    private val requestCounselMapper: RequestCounselMapper,
    private val responseCounselMapper: ResponseCounselMapper
): CounselRepository {

    override fun requestCounsel(requestCounselData: RequestCounselData): Flow<Result<ResponseCounselData>> = flow {
        val exceptionMessage = "ChillGuy가 잠시 부재중입니다. 잠시 후 다시 시도해주세요."
        val result = counchillorApi.requestCounsel(requestCounselMapper.mapToDto(requestCounselData))
        if(result.isSuccessful){
            val dto = result.body() ?: throw IOException(exceptionMessage)
            emit(Result.success(responseCounselMapper.mapToModel(dto)))
        }else{
            throw IOException(exceptionMessage)
        }
    }.catch { error ->
        Log.e("CounselRepositoryImpl", "$error")
        emit(Result.failure(error))
    }

}