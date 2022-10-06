package com.example.httpclientsample

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

private const val TIME_OUT = 60000

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

//    @Provides
//    @Singleton
//    fun provideNetworkService(
//        httpClient: HttpClient
//    ): NetworkService {
//        return NetworkService(httpClient)
//    }
//
//    @Provides
//    @Singleton
//    fun provideKtorHttpClient(): HttpClient = HttpClient(Android) {
//        install(ContentNegotiation) {
//            json(
//                json = Json {
//
//                },
//                contentType = ContentType.Application.Json
//            )
//
//            engine {
//                connectTimeout = TIME_OUT
//                socketTimeout = TIME_OUT
//            }
//        }
//
//        install(ResponseObserver) {
//            onResponse { response ->
//                Log.d("HTTP status:", "${response.status.value}")
//            }
//        }
//
//        install(DefaultRequest) {
//            header(HttpHeaders.ContentType, ContentType.Application.Json)
//        }
//    }
}