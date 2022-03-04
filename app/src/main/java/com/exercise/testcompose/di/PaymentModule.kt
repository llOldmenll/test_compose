package com.exercise.testcompose.di

import com.exercise.data.entity.PaymentRequest
import com.exercise.data.network.factory.NetworkServiceFactory
import com.exercise.data.network.mapper.PaymentDataToPaymentRequestMapper
import com.exercise.data.network.service.PaymentNetworkService
import com.exercise.data.repository.PaymentRepositoryImpl
import com.exercise.domain.entity.PaymentData
import com.exercise.domain.entity.ThreeDS
import com.exercise.domain.mapper.Mapper
import com.exercise.domain.repository.PaymentRepository
import com.exercise.domain.use_case.PayUseCase
import com.exercise.domain.use_case.SuspendedUseCase
import com.exercise.testcompose.mapper.CardStateToPaymentDataMapper
import com.exercise.testcompose.presentation.state.CardState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object PaymentModule {

    @Provides
    @ActivityRetainedScoped
    fun paymentRequestMapper(): Mapper<PaymentData, PaymentRequest> =
        PaymentDataToPaymentRequestMapper()

    @Provides
    @ActivityRetainedScoped
    fun paymentRepository(
        requestMapper: Mapper<PaymentData, PaymentRequest>,
        networkServiceFactory: NetworkServiceFactory
    ): PaymentRepository = PaymentRepositoryImpl(
        requestMapper,
        networkServiceFactory.create(PaymentNetworkService::class.java)
    )

    @Provides
    @ActivityRetainedScoped
    fun payUseCase(
        paymentRepository: PaymentRepository
    ): SuspendedUseCase<PaymentData, ThreeDS> = PayUseCase(paymentRepository)

    @Provides
    fun paymentDataMapper(): Mapper<CardState, PaymentData> = CardStateToPaymentDataMapper()
}