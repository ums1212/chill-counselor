package org.comon.chillcounselor.data.api

import org.comon.chillcounselor.data.dto.RequestCounselDto
import org.comon.chillcounselor.data.dto.ResponseCounselDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CounchillorApi {

    @POST("counchillor/counselling/")
    suspend fun requestCounsel(
        @Body requestBody: RequestCounselDto
    ): Response<ResponseCounselDto>

}