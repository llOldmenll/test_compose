package com.exercise.domain.use_case

import com.exercise.domain.entity.CardType
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class GetCardTypeUseCaseTest(
    private val cardNumber: String,
    private val cardType: CardType
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "When card number {0} passed, then card type {1} returned")
        fun data() = listOf(
            arrayOf("3530111333300000", CardType.JCB),
            arrayOf("378282246310005", CardType.Amex),
            arrayOf("34343434343434", CardType.Amex),
            arrayOf("30123456789019", CardType.DinersClub),
            arrayOf("36148900647913", CardType.DinersClub),
            arrayOf("4543474002249996", CardType.Visa),
            arrayOf("5436031030606378", CardType.MasterCard),
            arrayOf("5555555555554444", CardType.MasterCard),
            arrayOf("6011111111111117", CardType.Discover),
            arrayOf("6759649826438453", CardType.Maestro),
            arrayOf("6799990100000000019", CardType.Maestro),
            arrayOf("1111111111111111", CardType.Unknown),
            arrayOf("122000000000003", CardType.Unknown),
            arrayOf("", CardType.Unknown)
        )
    }

    private lateinit var getCardTypeUseCase: UseCase<String, CardType>

    @Before
    fun setUp() {
        getCardTypeUseCase = GetCardTypeUseCase()
    }

    @Test
    fun test() {
        // When
        val result = getCardTypeUseCase.execute(cardNumber)

        // Then
        assertTrue(result == cardType)
    }
}