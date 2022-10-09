package com.example.httpclientsample

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import javax.inject.Inject

class NetworkService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getBooks(): String =
        client.get {
            url("https://www.googleapis.com/books/v1/volumes?q=android")
        }.bodyAsText()
}