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
        val result = counchillorApi.requestCounsel(requestCounselMapper.mapToDto(requestCounselData))
        if(result.isSuccessful){
            Log.d("test1234", "$result")
            val dto = result.body() ?: throw IOException("Counchillor Api call isn't successful")
            emit(Result.success(responseCounselMapper.mapToModel(dto)))
        }else{
            throw IOException("Counchillor Api call isn't successful")
        }
    }.catch { error ->
        emit(Result.failure(error))
    }

}