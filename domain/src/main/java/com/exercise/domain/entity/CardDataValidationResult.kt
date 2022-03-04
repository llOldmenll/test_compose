package com.exercise.domain.entity

data class CardDataValidationResult(
    val isCardNumberValid: Boolean = false,
    val isExpiryDateValid: Boolean = false,
    val isCVVNumberValid: Boolean = false
) {
    fun isCardValid() = isCardNumberValid && isExpiryDateValid && isCVVNumberValid
}
