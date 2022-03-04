package com.exercise.domain.repository

import com.exercise.domain.entity.PaymentData
import com.exercise.domain.entity.ThreeDS

interface PaymentRepository {
    /**
     * @param data - contains card details needed for payment operation
     * @return ThreeDS, contains 3DS url and error message
     */
    suspend fun pay(data: PaymentData): ThreeDS
}