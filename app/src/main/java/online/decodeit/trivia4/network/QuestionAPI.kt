package online.decodeit.trivia4.network

import online.decodeit.trivia4.model.Question
import online.decodeit.trivia4.utilities.Constants.CATEGORIES
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

/**
 * This is our DAO
 * https://the-trivia-api.com/api/questions?categories=arts_and_literature&limit=5&difficulty=medium
 */
@Singleton
interface QuestionAPI {
//    @GET("questions?limit=50")
//    suspend fun getQuestions(): Question
//
//    @GET("questions?limit=50")
//    suspend fun getQuestions(@Query("categories") category: String): Question

    @GET("questions")
    suspend fun getQuestions(
        @Query("limit") max: Int = 1,
        @Query("categories") category: String = CATEGORIES["General Knowledge"].toString(),
        @Query("difficulty") difficulty: String = "easy"
    ): Question

}