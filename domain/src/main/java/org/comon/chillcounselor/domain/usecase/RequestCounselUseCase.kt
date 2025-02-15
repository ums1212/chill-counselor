package org.comon.chillcounselor.domain.usecase

import org.comon.chillcounselor.domain.model.RequestCounselData
import org.comon.chillcounselor.domain.repository.CounselRepository

class RequestCounselUseCase(
    private val counselRepository: CounselRepository,
) {

    operator fun invoke(requestCounselData: RequestCounselData) =
        counselRepository.requestCounsel(requestCounselData)

}