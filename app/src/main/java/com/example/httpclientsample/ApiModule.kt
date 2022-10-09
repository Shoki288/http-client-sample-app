package com.example.httpclientsample

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideKtorHttpClient(
        okHttpClient: OkHttpClient
    ): HttpClient = HttpClient(OkHttp) {
        engine {
            config {
                preconfigured = okHttpClient
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
        }
        install(ContentNegotiation) {
            register(ContentType.Application.Json, GsonConverter())
            // TODO xmlのconverterを作る
        }
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logInspector = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor {
                val url = it.request().url
                val requestBuilder = it.request().newBuilder().url(url)
                it.proceed(requestBuilder.build())
            }
            .addInterceptor(logInspector)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}