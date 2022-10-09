package com.example.httpclientsample

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class NetworkService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getBooks(): BookInfoListResponse =
        client.get {
            url("https://www.googleapis.com/books/v1/volumes?q=android")
        }.body()
}