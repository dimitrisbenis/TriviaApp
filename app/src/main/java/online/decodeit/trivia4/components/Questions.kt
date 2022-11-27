package online.decodeit.trivia4.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import online.decodeit.trivia4.model.QuestionItem
import online.decodeit.trivia4.screens.QuestionsViewModel
import online.decodeit.trivia4.ui.theme.Trivia4Theme

@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()

    val questionIndex = remember {
        mutableStateOf<Int>(0)
    }

    val receivedQuestions = remember {
        mutableStateOf<Int>(0)
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

//@Preview(showBackground = true)
@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionsViewModel,
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
                QuestionTracker(counter = questionIndex.value + 1)
                Divider(
                    modifier = Modifier.padding(20.dp),
                    color = MaterialTheme.colors.secondaryVariant
                )

                Column{
                    Text(
                        text = question.question,
                        modifier = Modifier
                            .align(alignment = Alignment.Start)
                            .padding(20.dp)
                            .fillMaxHeight(0.5f),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp,
                    color = MaterialTheme.colors.secondaryVariant
                    )
                    choicesState.forEachIndexed { index, answer ->
                        Row(
                            modifier = Modifier
                                .padding(3.dp)
                                .fillMaxWidth()
                                //.height(45.dp)
                                .border(
                                    width = 4.dp, brush = Brush.linearGradient(
                                        colors = listOf(
                                            MaterialTheme.colors.secondaryVariant,
                                            MaterialTheme.colors.secondaryVariant
                                        )
                                    ),
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .clip(
                                    RoundedCornerShape(
                                        topStartPercent = 50,
                                        topEndPercent = 50,
                                        bottomEndPercent = 50,
                                        bottomStartPercent = 50
                                    )
                                )
                                .background(Color.Transparent),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            RadioButton(
                                selected = (answered.value == index),
                                onClick = {
                                    updateAnswer(index)
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = if (correctAnswerState.value == true)
                                        Color.Green.copy(alpha = 0.5f) else
                                            Color.Red.copy(alpha = 0.5f)
                                )
                            )
                            val annotatedString = buildAnnotatedString {
                                withStyle(style = SpanStyle(
                                    fontWeight = FontWeight.Light,
                                    fontSize = 17.sp,
                                    color = if (correctAnswerState.value == true && index == answered.value) {
                                        Color.Green.copy(0.5f)
                                    } else if (correctAnswerState.value == false && index == answered.value) {
                                            Color.Red.copy(0.5f)
                                    } else {
                                            MaterialTheme.colors.secondaryVariant
                                    }
                                )){
                                    append(answer)
                                }
                            }
                            Text(
                                text = annotatedString
                            )
                        }
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
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                            .padding(15.dp)
                    ) {
                        Text(text = "Next")
                    }
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun QuestionTracker(counter: Int = 0, outOf: Int = 5) {
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,
            fontSize = 27.sp,
            color = MaterialTheme.colors.secondaryVariant)) {
                append("Question $counter / $outOf")
            }
        }
    })
}