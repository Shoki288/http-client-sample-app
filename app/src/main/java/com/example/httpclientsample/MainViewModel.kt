package com.example.httpclientsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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
}