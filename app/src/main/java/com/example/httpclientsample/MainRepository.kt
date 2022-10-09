package com.example.httpclientsample

import io.reactivex.Single
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: NetworkService
) {
    suspend fun getBook(): BookInfoListResponse = service.getBooks()

    fun getBookForRxJava(): Single<BookInfoListResponse> = service.getBooksForSingle()
}