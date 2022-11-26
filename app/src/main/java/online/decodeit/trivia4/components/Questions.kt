package online.decodeit.trivia4.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import online.decodeit.trivia4.screens.QuestionsViewModel
import online.decodeit.trivia4.ui.theme.Trivia4Theme

@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()
    if (viewModel.data.value.loading == true)
        Text(text = "LOADING")
    else {
        questions?.forEach{
            Log.d("Result", "Questions: ${it.question}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionDisplay() {
    Trivia4Theme(darkTheme = true) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(4.dp),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                QuestionTracker()
                Divider(
                    modifier = Modifier.padding(20.dp),
                    color = MaterialTheme.colors.secondaryVariant
                )
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