package online.decodeit.trivia4.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnswerRow(
    answered: MutableState<Int?>,
    index: Int,
    answer: String,
    correctAnswerState: MutableState<Boolean?>,
    updateAnswer: (Int) -> Unit
) {
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
            text = annotatedString,
            modifier = Modifier.padding(top = 3.dp, bottom = 3.dp)
        )
    }
}