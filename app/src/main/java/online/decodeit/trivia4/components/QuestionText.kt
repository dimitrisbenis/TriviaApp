package online.decodeit.trivia4.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuestionText(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxHeight(0.3f),
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.sp,
        color = MaterialTheme.colors.secondaryVariant
    )
}