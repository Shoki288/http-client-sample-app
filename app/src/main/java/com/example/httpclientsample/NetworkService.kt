package com.example.httpclientsample

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getBooks(): BookInfoListResponse =
        client.get {
            url("https://www.googleapis.com/books/v1/volumes?q=android")
        }.body()

    fun getBooksForSingle(): Single<BookInfoListResponse> {
        return Single.create { emitter ->
            try {
                // TODO やり方が思いつかなかったため、GlobalScopeを使用している。
                //  必ず違う方法を見つけて差し替えること
                GlobalScope.launch {
                    val response =
                        client.get {
                            url("https://www.googleapis.com/books/v1/volumes?q=android")
                        }.body<BookInfoListResponse>()
                    emitter.onSuccess(response)
                }
            } catch (e: Throwable) {
                emitter.onError(e)
            }
        }
    }
}