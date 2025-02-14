package org.comon.chillcounselor.data.mapper

import org.comon.chillcounselor.data.dto.RequestCounselDto
import org.comon.chillcounselor.domain.model.RequestCounselData

class RequestCounselMapper {

    fun mapToDto(requestCounselData: RequestCounselData): RequestCounselDto
    = RequestCounselDto(
        counselContent = requestCounselData.counselContent
    )

}