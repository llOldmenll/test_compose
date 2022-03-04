package com.exercise.data.network.factory

import retrofit2.Converter

/**
 * Used for converter factory creation
 */
interface ConverterFactory {
    /**
     * Create converter factory
     *
     * @return Converter.Factory
     */
    fun create(): Converter.Factory
}