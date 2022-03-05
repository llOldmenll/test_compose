package com.exercise.domain.use_case

import com.exercise.domain.entity.PaymentData
import com.exercise.domain.entity.ThreeDS
import com.exercise.domain.repository.PaymentRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.then
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PayUseCaseTest {

    private val paymentRepository: PaymentRepository = mock()
    private lateinit var payUseCase: PayUseCase

    @Before
    fun setUp() {
        payUseCase = PayUseCase(paymentRepository)
    }

    @Test
    fun `When payment data is null then return empty data error message`() = runBlockingTest {
        // Given
        val expected = ThreeDS(errorMessage = "Error: Empty payment data.")

        // When
        val result = payUseCase.execute(null)

        // Then
        then(paymentRepository).shouldHaveNoInteractions()
        assertEquals(expected, result)
    }

    @Test
    fun `When payment data is not null then payment repository is invoked`() = runBlockingTest {
        // Given
        val paymentData = PaymentData("", "", "", "")

        // When
        payUseCase.execute(paymentData)

        // Then
        then(paymentRepository).should().pay(paymentData)
    }
}