package online.decodeit.trivia4.utilities

object Constants {
    // https://the-trivia-api.com/api/categories
    // https://the-trivia-api.com/api/questions
    // https://the-trivia-api.com/api/metadata
    const val BASE_URL = "https://the-trivia-api.com/api/"
    val CATEGORIES = mapOf(
        "Arts & Literature" to "arts_and_literature",
        "Film & TV" to "film_and_tv",
        "Food & Drink" to "food_and_drink",
        "General Knowledge" to "general_knowledge",
        "Geography" to "geography",
        "History" to "history",
        "Music" to "music",
        "Science" to "science",
        "Society & Culture" to "society_and_culture",
        "Sport & Leisure" to "sport_and_leisure"
    )
}