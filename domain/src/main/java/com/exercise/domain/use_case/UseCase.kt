package com.exercise.domain.use_case

/**
 * Used for creation of use case
 */
interface UseCase<DATA, T> {
    /**
     * Execute use case
     *
     * @param data - input data, nullable
     * @return Specified data type
     */
    fun execute(data: DATA? = null): T
}