package com.exercise.domain.use_case

/**
 * Used for creation of use case with suspend function
 */
interface SuspendedUseCase<DATA, T> {
    /**
     * Execute Suspended use case
     *
     * @param data - input data, nullable
     * @return Specified data type
     */
    suspend fun execute(data: DATA? = null): T
}