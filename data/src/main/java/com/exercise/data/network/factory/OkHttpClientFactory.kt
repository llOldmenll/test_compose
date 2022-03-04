package com.exercise.data.network.factory

import okhttp3.OkHttpClient

/**
 * Used for OkHttp client creation and configuration
 */
interface OkHttpClientFactory {
    /**
     * Create OkHttp client
     *
     * @return OkHttpClient
     */
    fun create(): OkHttpClient
}