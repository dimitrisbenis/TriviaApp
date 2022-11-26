package online.decodeit.trivia4.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import online.decodeit.trivia4.data.DataOrException
import online.decodeit.trivia4.model.QuestionItem
import online.decodeit.trivia4.repository.QuestionRepository
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionRepository) : ViewModel() {
    val data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        getQuestions()
    }

    private fun getQuestions() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getQuestions()
            if (data.value.data.toString().isNotEmpty())
                data.value.loading = false
        }
    }
}