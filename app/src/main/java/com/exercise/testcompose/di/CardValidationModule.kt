package com.exercise.testcompose.di

import com.exercise.domain.entity.CardData
import com.exercise.domain.entity.CardDataValidationResult
import com.exercise.domain.entity.CardType
import com.exercise.domain.mapper.Mapper
import com.exercise.domain.use_case.GetCardTypeUseCase
import com.exercise.domain.use_case.UseCase
import com.exercise.domain.use_case.ValidateCardDataUseCase
import com.exercise.testcompose.mapper.CardStateToCardDataMapper
import com.exercise.testcompose.presentation.state.CardState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CardValidationModule {

    @Provides
    @ViewModelScoped
    fun getCardTypeUseCase(): UseCase<String, CardType> = GetCardTypeUseCase()

    @Provides
    @ViewModelScoped
    fun validateCardDataUseCase(): UseCase<CardData, CardDataValidationResult> =
        ValidateCardDataUseCase()

    @Provides
    @ViewModelScoped
    fun cardDataMapper(): Mapper<CardState, CardData> = CardStateToCardDataMapper()
}