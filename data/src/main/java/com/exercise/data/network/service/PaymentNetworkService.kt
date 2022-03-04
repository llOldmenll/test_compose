package com.exercise.data.network.service

import com.exercise.data.entity.ThreeDSEntity
import com.exercise.data.entity.PaymentRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentNetworkService {

    @POST("pay")
    suspend fun pay(@Body request: PaymentRequest): Response<ThreeDSEntity>
}