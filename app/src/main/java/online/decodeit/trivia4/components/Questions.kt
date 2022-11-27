package online.decodeit.trivia4.components

import android.util.Log
import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import online.decodeit.trivia4.model.QuestionItem
import online.decodeit.trivia4.screens.QuestionsViewModel

@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()

    val questionIndex = remember {
        mutableStateOf(0)
    }

    val receivedQuestions = remember {
        mutableStateOf(0)
    }

    val context = LocalContext.current

    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator()
    } else {
        val question = try {
            questions?.get(questionIndex.value)
        } catch (ex: Exception) {
            Log.d("Exception", "Questions: No question!!!!! ${ex.localizedMessage}")
        }
        if(questions != null) {
            receivedQuestions.value = questions.size
            QuestionDisplay(
                question = question as QuestionItem,
                questionIndex = questionIndex,
                receivedQuestions,
                viewModel = viewModel,
            ){
                if (questionIndex.value + 1 < receivedQuestions.value) {
                    questionIndex.value += 1
                } else {
                    Toast.makeText(context, "Round Finished", Toast.LENGTH_LONG).show()
                    questionIndex.value = 0
                }
            }
        }
    }
}

