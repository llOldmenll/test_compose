package com.exercise.testcompose.mapper

import com.exercise.domain.entity.CardData
import com.exercise.domain.mapper.Mapper
import com.exercise.testcompose.presentation.state.CardState

class CardStateToCardDataMapper : Mapper<CardState, CardData> {

    override fun map(from: CardState): CardData = CardData(
        from.cardNumber.value,
        from.expiryDate.value,
        from.cvvNumber.value,
        from.cardType.value
    )
}