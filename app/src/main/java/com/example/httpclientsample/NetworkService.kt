package com.example.httpclientsample

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

//class NetworkService(private val client: HttpClient) {
//    suspend fun getBooks(): BookInfoListResponse =
//        client.get("https://www.googleapis.com/books/v1/volumes?q=android").body()
//}