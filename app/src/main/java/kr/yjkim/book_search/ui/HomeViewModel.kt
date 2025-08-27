package kr.yjkim.book_search.ui

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import kr.yjkim.book_search.data.BookSearchRepository

class HomeViewModel(private val repository: BookSearchRepository): ViewModel() {

    private val _keyword = MutableLiveData<String>()
    val keyword: LiveData<String> = _keyword

    fun searchKeyword(keyword: String) {
        viewModelScope.launch {
            repository.searchKeyword(keyword)
        }
        _keyword.value = keyword
    }

    companion object {
        fun create(repo: BookSearchRepository): ViewModelProvider.Factory {
            return viewModelFactory {
                initializer {
                    HomeViewModel(repo)
                }
            }
        }
    }
}