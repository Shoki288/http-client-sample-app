package com.example.httpclientsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
    val bookInfoStr = MutableStateFlow("Hi Ktor")

    fun getBookInfo() {
        viewModelScope.launch {
            val response = repository.getBook()
            bookInfoStr.value = response.items.joinToString(separator = "\n") {
                it.volumeInfo.title
            }
        }
    }

    fun getBookInfoRxJava() {
        repository.getBookForRxJava()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<BookInfoListResponse> {
                override fun onSubscribe(d: Disposable) {
                    bookInfoStr.value = "onSubscribe"
                }

                override fun onSuccess(t: BookInfoListResponse) {
                    bookInfoStr.update {
                        it.plus(t.items.joinToString("\n") { it.volumeInfo.title })
                    }
                }

                override fun onError(e: Throwable) {
                    bookInfoStr.update {
                        it.plus("onError")
                    }
                }

            })

    }
}