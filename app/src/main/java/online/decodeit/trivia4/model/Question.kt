package online.decodeit.trivia4.model

/**
 * These Questions are retrieved from https://opentdb.com/api_config.php
 */
data class Question(
    val response_code: Int,
    val results: List<Result>
)