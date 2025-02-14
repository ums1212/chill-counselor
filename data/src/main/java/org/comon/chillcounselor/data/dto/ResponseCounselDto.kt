package org.comon.chillcounselor.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseCounselDto(
    @Json(name = "message")
    val message: String = "",
    @Json(name = "advice")
    val advice: String = "",
    @Json(name = "chillness_level")
    val chillnessLevel: Int = 0,
    @Json(name = "error")
    val error: String = "",
    @Json(name = "reason")
    val reason: String = "",
)