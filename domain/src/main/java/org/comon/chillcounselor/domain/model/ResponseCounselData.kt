package org.comon.chillcounselor.domain.model

data class ResponseCounselData(
    val message: String = "",
    val advice: String = "",
    val chillnessLevel: Int = 0,
    val error: String = "",
    val reason: String = "",
)