package com.exercise.data.entity

import com.exercise.data.utils.FAILURE_URL
import com.exercise.data.utils.SUCCESS_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaymentRequest(
    @Json(name = "expiry_month")
    val expiryMonth: String,
    @Json(name = "expiry_year")
    val expiryYear: String,
    @Json(name = "cvv")
    val cvv: String,
    @Json(name = "number")
    val number: String,
    @Json(name = "success_url")
    val successUrl: String = SUCCESS_URL,
    @Json(name = "failure_url")
    val failureUrl: String = FAILURE_URL
)
