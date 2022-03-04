package com.exercise.domain.entity

data class CardData(
    val cardNumber: String,
    val expiryDate: String,
    val cvvNumber: String,
    val cardType: CardType
)
