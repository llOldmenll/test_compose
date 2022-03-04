package com.exercise.domain.use_case

import com.exercise.domain.entity.CardData
import com.exercise.domain.entity.CardDataValidationResult
import com.exercise.domain.entity.CardType
import java.util.*

class ValidateCardDataUseCase : UseCase<CardData, CardDataValidationResult> {
    override fun execute(data: CardData?): CardDataValidationResult = data?.let {
        CardDataValidationResult(
            validateCardNumber(data.cardNumber),
            validateExpiryDate(data.expiryDate),
            validateCVV(data.cvvNumber, data.cardType)
        )
    } ?: CardDataValidationResult()

    // TODO Implement card number validation according to a card type
    private fun validateCardNumber(number: String): Boolean =
        number.matches(Regex("^[0-9]{10,16}$"))

    private fun validateExpiryDate(date: String): Boolean {
        if (date.length < 4 || date.any { !it.isDigit() }) return false

        val currentTime = Calendar.getInstance(Locale.ROOT)
        val currentYear = currentTime.get(Calendar.YEAR) % 100
        val currentMonth = currentTime.get(Calendar.MONTH) + 1
        val expMonth = date.substring(0..1).toInt()
        val expYear = date.substring(2..3).toInt()

        return expMonth in 1..12 &&
                (expYear > currentYear || (expYear == currentYear && expMonth >= currentMonth))
    }

    private fun validateCVV(cvv: String, cardType: CardType): Boolean {
        return if (cardType == CardType.Amex) cvv.matches(Regex("^[0-9]{4}$"))
        else cvv.matches(Regex("^[0-9]{3}$"))
    }
}