package com.exercise.testcompose.presentation.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.exercise.testcompose.presentation.state.CardState
import com.exercise.testcompose.R
import com.exercise.testcompose.presentation.filters.*

@Composable
fun CardInputFields(
    cardState: CardState,
    modifier: Modifier
) {
    val (cardNumber, cardNumberSetter) = cardState.cardNumber
    val (cardHolderName, cardHolderNameSetter) = cardState.cardHolderName
    val (expiryDate, expiryDateSetter) = cardState.expiryDate
    val (cvvNumber, cvvNumberSetter) = cardState.cvvNumber

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        /* Card Holder */
        InputField(
            text = cardHolderName,
            label = stringResource(id = R.string.card_holder_name),
            onTextChanged = cardHolderNameSetter,
            modifier = Modifier.fillMaxWidth()
        )

        /* Card Number */
        InputField(
            text = cardNumber,
            label = stringResource(id = R.string.card_number),
            isError = cardState.cardNumberError,
            keyboardType = KeyboardType.Number,
            onTextChanged = cardNumberSetter,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = CardNumberTransformation,
            maxLength = MAX_CARD_NUMBER_LENGTH
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /* Card Expiry Date */
            InputField(
                text = expiryDate,
                label = stringResource(id = R.string.expiry_date),
                isError = cardState.expiryDateError,
                keyboardType = KeyboardType.Number,
                onTextChanged = expiryDateSetter,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                maxLength = MAX_EXPIRY_DATE_LENGTH
            )

            /* Card CVV Number */
            InputField(
                text = cvvNumber,
                label = stringResource(id = R.string.cvc),
                isError = cardState.cvvNumberError,
                keyboardType = KeyboardType.Number,
                onTextChanged = cvvNumberSetter,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                maxLength = MAX_CVV_LENGTH
            )
        }
    }
}