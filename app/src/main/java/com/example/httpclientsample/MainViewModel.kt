package com.example.httpclientsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
    private val _book = MutableStateFlow<BookInfoListResponse?>(BookInfoListResponse(items = emptyList()))
    val bookInfoStr = _book.map { res ->
        res?.let {
            val str = ""
            it.items.forEach { bookInfo ->
                str.plus("id: ${bookInfo.id}\n")
                str.plus("volumeInfo: ${bookInfo.volumeInfo}\n")
                str.plus("title: ${bookInfo.volumeInfo.title}\n")
                str.plus("author: ${bookInfo.volumeInfo.author}\n")
                str.plus("publisher: ${bookInfo.volumeInfo.publisher}\n")
                str.plus("publishedDate: ${bookInfo.volumeInfo.publishedDate}\n")
                str.plus("description: ${bookInfo.volumeInfo.description}\n")
                str.plus("pageCount: ${bookInfo.volumeInfo.pageCount}\n")
                str.plus("categories: ${bookInfo.volumeInfo.categories}\n")
                str.plus("averageReviewRate: ${bookInfo.volumeInfo.averageReviewRate}\n")
                str.plus("totalReviewCount: ${bookInfo.volumeInfo.totalReviewCount}\n")
                str.plus("imageUrl: ${bookInfo.volumeInfo.images.imageUrl}\n")
                str.plus("language: ${bookInfo.volumeInfo.language}\n")
                str.plus("previewLink: ${bookInfo.volumeInfo.previewLink}\n")
                str.plus("isFavorite: ${bookInfo.volumeInfo.isFavorite}\n")
                str.plus("saleInfo: ${bookInfo.saleInfo}\n")
                str.plus("price: ${bookInfo.saleInfo.listPrice.price}\n")
                str.plus("accessInfo: ${bookInfo.accessInfo}\n")
                str.plus("downloadAccess.message: ${bookInfo.accessInfo?.downloadAccess?.message ?: "message"}\n")
            }
            return@map str
        } ?: "null"
    }.stateIn(viewModelScope, SharingStarted.Eagerly, "none")

    fun getBookInfo() {
        viewModelScope.launch {
            val response = repository.getBook()
            _book.value = response
        }
    }
}