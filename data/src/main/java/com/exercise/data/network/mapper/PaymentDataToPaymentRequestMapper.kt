package com.exercise.data.network.mapper

import com.exercise.domain.entity.PaymentData
import com.exercise.data.entity.PaymentRequest
import com.exercise.domain.mapper.Mapper

class PaymentDataToPaymentRequestMapper : Mapper<PaymentData, PaymentRequest> {

    override fun map(from: PaymentData): PaymentRequest = PaymentRequest(
        from.expiryMonth,
        from.expiryYear,
        from.cvv,
        from.number
    )
}