package com.example.httpclientsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val bookInfoStr = MutableStateFlow("Hi Ktor")

    fun getBookInfo() {
        val client = HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = 10000
            }
        }
        val address = Url("https://www.googleapis.com/books/v1/volumes?q=android")
        viewModelScope.launch {
            val response = client.get {
                url(address.toString())
            }.bodyAsText()

            bookInfoStr.value = response.run { take(500).plus("\n\nHallo Kator") }
        }
    }
}