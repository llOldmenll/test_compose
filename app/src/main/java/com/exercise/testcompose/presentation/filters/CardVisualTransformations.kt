package com.exercise.testcompose.presentation.filters

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.lang.StringBuilder

const val MAX_CARD_NUMBER_LENGTH = 16
const val MAX_EXPIRY_DATE_LENGTH = 4
const val MAX_CVV_LENGTH = 4

val CardNumberTransformation = VisualTransformation {
    val trimmed =
        if (it.text.length >= MAX_CARD_NUMBER_LENGTH) it.text.substring(0 until MAX_CARD_NUMBER_LENGTH)
        else it.text
    val strBuilder = StringBuilder()

    for (i in trimmed.indices) {
        strBuilder.append(trimmed[i])
        if (i % 4 == 3 && i != MAX_CARD_NUMBER_LENGTH - 1) strBuilder.append(" ")
    }

    TransformedText(AnnotatedString(strBuilder.toString()), creditCardOffsetMapping)
}

private val creditCardOffsetMapping = object : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = when {
        offset <= 3 -> offset
        offset <= 7 -> offset + 1
        offset <= 11 -> offset + 2
        offset <= 16 -> offset + 3
        else -> 19
    }

    override fun transformedToOriginal(offset: Int): Int = when {
        offset <= 4 -> offset
        offset <= 9 -> offset - 1
        offset <= 14 -> offset - 2
        offset <= 19 -> offset - 3
        else -> 16
    }
}