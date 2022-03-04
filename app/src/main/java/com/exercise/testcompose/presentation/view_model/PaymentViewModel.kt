package com.exercise.testcompose.presentation.view_model

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.domain.entity.*
import com.exercise.domain.mapper.Mapper
import com.exercise.domain.use_case.SuspendedUseCase
import com.exercise.domain.use_case.UseCase
import com.exercise.testcompose.presentation.state.CardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getCardTypeUseCase: UseCase<String, CardType>,
    private val payUseCase: SuspendedUseCase<PaymentData, ThreeDS>,
    private val validateCardUseCase: UseCase<CardData, CardDataValidationResult>,
    private val cardDataMapper: Mapper<CardState, CardData>,
    private val paymentDataMapper: Mapper<CardState, PaymentData>,
) : ViewModel() {

    val cardState = CardState()

    init {
        setupCardTypeDefinition()
        setupErrorsReset()
    }

    fun pay(onResult: (ThreeDS) -> Unit) {
        val validationResult = validateCardUseCase.execute(cardDataMapper.map(cardState))

        if (validationResult.isCardValid()) get3DSLink(onResult)
        else updateValidationErrors(validationResult)
    }

    private fun setupCardTypeDefinition() {
        snapshotFlow { cardState.cardNumber.value }
            .onEach { cardState.cardType.value = getCardTypeUseCase.execute(it) }
            .launchIn(viewModelScope)
    }

    private fun setupErrorsReset() {
        snapshotFlow { cardState.cardNumber.value }
            .onEach { cardState.cardNumberError.value = false }
            .launchIn(viewModelScope)
        snapshotFlow { cardState.expiryDate.value }
            .onEach { cardState.expiryDateError.value = false }
            .launchIn(viewModelScope)
        snapshotFlow { cardState.cvvNumber.value }
            .onEach { cardState.cvvNumberError.value = false }
            .launchIn(viewModelScope)
    }

    private fun updateValidationErrors(validationResult: CardDataValidationResult) =
        cardState.apply {
            cardNumberError.value = !validationResult.isCardNumberValid
            expiryDateError.value = !validationResult.isExpiryDateValid
            cvvNumberError.value = !validationResult.isCVVNumberValid
        }

    private fun get3DSLink(onResult: (ThreeDS) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val threeDS = payUseCase.execute(paymentDataMapper.map(cardState))
            println(threeDS.toString())
            withContext(Dispatchers.Main) { onResult(threeDS) }
        }
    }
}