package online.decodeit.trivia4.utilities

object Constants {
    // https://the-trivia-api.com/api/categories
    // https://the-trivia-api.com/api/questions
    // https://the-trivia-api.com/api/metadata
    const val GENERAL_KNOWLEDGE = "General Knowledge"
    const val ARTS_LITERATURE = "Arts & Literature"
    const val FILM_TV = "Film & TV"
    const val FOOD_DRINK = "Food & Drink"
    const val GEOGRAPHY = "Geography"
    const val HISTORY = "History"
    const val MUSIC = "Music"
    const val SCIENCE = "Science"
    const val SOCIETY_CULTURE = "Society & Culture"
    const val SPORT_LEISURE = "Sport & Leisure"

    const val BASE_URL = "https://the-trivia-api.com/api/"
    val CATEGORIES = mapOf(
        ARTS_LITERATURE to "arts_and_literature",
        FILM_TV to "film_and_tv",
        FOOD_DRINK to "food_and_drink",
        GENERAL_KNOWLEDGE to "general_knowledge",
        GEOGRAPHY to "geography",
        HISTORY to "history",
        MUSIC to "music",
        SCIENCE to "science",
        SOCIETY_CULTURE to "society_and_culture",
        SPORT_LEISURE to "sport_and_leisure"
    )
}