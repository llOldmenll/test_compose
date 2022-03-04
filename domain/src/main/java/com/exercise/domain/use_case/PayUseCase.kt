package com.exercise.domain.use_case

import com.exercise.domain.entity.PaymentData
import com.exercise.domain.entity.ThreeDS
import com.exercise.domain.repository.PaymentRepository

class PayUseCase(
    private val paymentRepository: PaymentRepository
) : SuspendedUseCase<PaymentData, ThreeDS> {
    
    override suspend fun execute(data: PaymentData?): ThreeDS = data?.let {
        paymentRepository.pay(it)
    } ?: ThreeDS(errorMessage = "Error: Empty payment data.")
}