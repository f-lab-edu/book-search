package kr.yjkim.book_search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.yjkim.book_search.data.BookSearchRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: BookSearchRepository): ViewModel() {

    private val _keyword: MutableStateFlow<String> = MutableStateFlow("")
    val keyword: StateFlow<String> = _keyword.asStateFlow()

    fun searchKeyword(keyword: String) {
        viewModelScope.launch {
            repository.searchKeyword(keyword)
        }
        _keyword.value = keyword
    }
}