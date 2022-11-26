package online.decodeit.trivia4.network

import online.decodeit.trivia4.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionAPI {
    @GET("questions")
    suspend fun getQuestions(): Question
}