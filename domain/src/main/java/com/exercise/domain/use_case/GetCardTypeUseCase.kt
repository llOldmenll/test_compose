package com.exercise.domain.use_case

import com.exercise.domain.entity.CardType
import com.exercise.domain.entity.CardType.MasterCard
import com.exercise.domain.entity.CardType.JCB
import com.exercise.domain.entity.CardType.Amex
import com.exercise.domain.entity.CardType.DinersClub
import com.exercise.domain.entity.CardType.Visa
import com.exercise.domain.entity.CardType.Discover
import com.exercise.domain.entity.CardType.Maestro
import com.exercise.domain.entity.CardType.Unknown

class GetCardTypeUseCase : UseCase<String, CardType> {

    override fun execute(data: String?): CardType = data?.let { defineCardType(it) } ?: Unknown

    private fun defineCardType(cardNumber: String): CardType {
        val trimmedCardNumber = cardNumber.replace(" ", "")

        return when {
            trimmedCardNumber.matches(jcbRegex) -> JCB
            trimmedCardNumber.matches(ameRegex) -> Amex
            trimmedCardNumber.matches(dinersRegex) -> DinersClub
            trimmedCardNumber.matches(visaRegex) -> Visa
            trimmedCardNumber.matches(masterCardRegex) -> MasterCard
            trimmedCardNumber.matches(discoverRegex) -> Discover
            trimmedCardNumber.matches(maestroRegex) -> if (cardNumber[0] == '5') MasterCard else Maestro
            else -> Unknown
        }
    }

    private companion object {
        val jcbRegex = Regex("^(?:2131|1800|35)[0-9]{0,}$")
        val ameRegex = Regex("^3[47][0-9]{0,}\$")
        val dinersRegex = Regex("^3(?:0[0-59]{1}|[689])[0-9]{0,}\$")
        val visaRegex = Regex("^4[0-9]{0,}\$")
        val masterCardRegex = Regex("^(5[1-5]|222[1-9]|22[3-9]|2[3-6]|27[01]|2720)[0-9]{0,}\$")
        val maestroRegex = Regex("^(5[06789]|6)[0-9]{0,}\$")
        val discoverRegex =
            Regex("^(6011|65|64[4-9]|62212[6-9]|6221[3-9]|622[2-8]|6229[01]|62292[0-5])[0-9]{0,}\$")
    }
}