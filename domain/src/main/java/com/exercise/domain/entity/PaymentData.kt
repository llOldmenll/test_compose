package com.exercise.domain.entity

data class PaymentData(
    val number: String,
    val expiryMonth: String,
    val expiryYear: String,
    val cvv: String
)
