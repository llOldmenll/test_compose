package com.exercise.testcompose.mapper

import com.exercise.domain.entity.PaymentData
import com.exercise.domain.mapper.Mapper
import com.exercise.testcompose.presentation.state.CardState
import java.util.*

class CardStateToPaymentDataMapper : Mapper<CardState, PaymentData> {

    override fun map(from: CardState): PaymentData = PaymentData(
        from.cardNumber.value,
        from.expiryDate.value.substring(0..1),
        getYear(from),
        from.cvvNumber.value
    )

    private fun getYear(from: CardState): String {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()
        val expiryYearPrefix = currentYear.substring(0..currentYear.length - 3)
        val expiryYear = from.expiryDate.value.substring(2..3)
        return "$expiryYearPrefix$expiryYear"
    }
}