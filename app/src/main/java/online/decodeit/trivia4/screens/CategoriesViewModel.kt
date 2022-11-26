package online.decodeit.trivia4.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import online.decodeit.trivia4.data.DataOrException
import online.decodeit.trivia4.model.CategoryList
import online.decodeit.trivia4.repository.CategoryRepository
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel() {
    private val data: MutableState<DataOrException<CategoryList, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getCategories()
            if (data.value.data.toString().isNotEmpty())
                data.value.loading = false
        }
    }
}