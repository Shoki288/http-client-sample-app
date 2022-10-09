package com.example.httpclientsample

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideKtorHttpClient(): HttpClient = HttpClient(CIO) {
        install(HttpTimeout) {
            requestTimeoutMillis = 10000
        }
    }
}