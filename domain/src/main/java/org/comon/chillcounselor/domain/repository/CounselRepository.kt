package org.comon.chillcounselor.domain.repository

import org.comon.chillcounselor.domain.model.ResponseCounselData
import kotlinx.coroutines.flow.Flow
import org.comon.chillcounselor.domain.model.RequestCounselData

interface CounselRepository {
    fun requestCounsel(requestCounselData: RequestCounselData): Flow<Result<ResponseCounselData>>
}