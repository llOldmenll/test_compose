package com.exercise.data.repository

import com.exercise.domain.entity.PaymentData
import com.exercise.data.entity.PaymentRequest
import com.exercise.domain.entity.ThreeDS
import com.exercise.domain.mapper.Mapper
import com.exercise.data.network.service.PaymentNetworkService
import com.exercise.domain.repository.PaymentRepository

class PaymentRepositoryImpl constructor(
    private val requestMapper: Mapper<PaymentData, PaymentRequest>,
    private val networkService: PaymentNetworkService
) : PaymentRepository {

    override suspend fun pay(data: PaymentData): ThreeDS {
        val response = networkService.pay(requestMapper.map(data))
        val url = response.body()?.url ?: ""

        return if (response.isSuccessful && url.isNotEmpty()) ThreeDS(url = url)
        else ThreeDS(errorMessage = "Error: ${response.message()}")
    }
}