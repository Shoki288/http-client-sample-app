package com.example.httpclientsample

import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: NetworkService
) {
    suspend fun getBook(): BookInfoListResponse = service.getBooks()
}