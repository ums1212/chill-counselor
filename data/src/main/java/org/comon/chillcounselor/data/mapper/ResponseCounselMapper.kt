package org.comon.chillcounselor.data.mapper

import org.comon.chillcounselor.data.dto.ResponseCounselDto
import org.comon.chillcounselor.domain.model.ResponseCounselData

class ResponseCounselMapper {

    fun mapToModel(responseCounselDto: ResponseCounselDto): ResponseCounselData =
        ResponseCounselData(
            message = responseCounselDto.message,
            advice = responseCounselDto.advice,
            chillnessLevel = responseCounselDto.chillnessLevel,
            error = responseCounselDto.error,
            reason = responseCounselDto.reason,
        )

}