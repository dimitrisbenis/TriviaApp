package online.decodeit.trivia4.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import online.decodeit.trivia4.model.QuestionItem
import online.decodeit.trivia4.screens.QuestionsViewModel
import online.decodeit.trivia4.ui.theme.Trivia4Theme

@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    receivedQuestions: MutableState<Int>,
    score: MutableState<Int>,
    viewModel: QuestionsViewModel,
    updateScore: (Int) -> Unit,
    onNextClicked: (Int) -> Unit
) {
    val choicesState = remember(question) {
        question.incorrectAnswers.toMutableList()
    }
    val correctAnswer = remember (question) {
        question.correctAnswer
    }
    choicesState.add(correctAnswer)
    choicesState.shuffle()

    val correctAnswerState = remember {
        mutableStateOf<Boolean?>(null)
    }
    val answered = remember {
        mutableStateOf<Int?>(null)
    }

    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answered.value = it
            correctAnswerState.value = (choicesState[it] == question.correctAnswer)
            if(correctAnswerState.value == true)
                updateScore(score.value)
        }
    }

    Trivia4Theme(darkTheme = true) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ScoreTracker(
                    answered = questionIndex.value + 1,
                    total = receivedQuestions.value,
                    score = score.value
                )
                QuestionTracker(
                    counter = questionIndex.value + 1,
                    outOf = receivedQuestions.value
                )
                Divider(
                    modifier = Modifier.padding(20.dp),
                    color = MaterialTheme.colors.secondaryVariant
                )

                Column (
                    modifier = Modifier.align(alignment = Alignment.Start)
                ) {

                    QuestionText(
                        text = question.question
                    )

                    choicesState.forEachIndexed { index, answer ->
                        AnswerRow(
                            answered= answered,
                            index = index,
                            answer = answer,
                            correctAnswerState = correctAnswerState,
                            updateAnswer = updateAnswer
                        )
                    }

                    Button(
                        onClick = { onNextClicked(questionIndex.value)
                            correctAnswerState.value = null
                            answered.value = null
                        },
                        shape = RoundedCornerShape(34.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.secondaryVariant,
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(15.dp)
                    ) {
                        Text(text = "Next")
                    }
                }
            }

        }
    }
}