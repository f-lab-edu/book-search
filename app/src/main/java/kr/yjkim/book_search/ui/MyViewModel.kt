package kr.yjkim.book_search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.yjkim.book_search.data.MyRepository

class MyViewModel(private val repository: MyRepository) : ViewModel() {

    private var _keyword: String = ""
    val keyword: String
        get() = _keyword

    val books = repository.books

    fun searchKeyword(word: String) {
        _keyword = word
        viewModelScope.launch {
            repository.searchKeyword(word)
        }
    }
}

class MyViewModelFactory(private val repository: MyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}