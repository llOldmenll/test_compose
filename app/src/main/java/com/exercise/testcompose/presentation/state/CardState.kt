package com.exercise.testcompose.presentation.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.exercise.domain.entity.CardType

data class CardState(
    val cardNumber: MutableState<String> = mutableStateOf(""),
    val cardHolderName: MutableState<String> = mutableStateOf(""),
    val expiryDate: MutableState<String> = mutableStateOf(""),
    val cvvNumber: MutableState<String> = mutableStateOf(""),
    val cardType: MutableState<CardType> = mutableStateOf(CardType.Unknown),
    val cardNumberError: MutableState<Boolean> = mutableStateOf(false),
    val expiryDateError: MutableState<Boolean> = mutableStateOf(false),
    val cvvNumberError: MutableState<Boolean> = mutableStateOf(false)
)
