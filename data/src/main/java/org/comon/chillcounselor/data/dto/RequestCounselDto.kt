package org.comon.chillcounselor.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestCounselDto(
    @Json(name = "counsel_content")
    val counselContent: String
)