package online.decodeit.trivia4.model

data class ByCategory(
    val questionsPerCategory: Map<String, Int> = mapOf<String, Int>()
)