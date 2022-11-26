package online.decodeit.trivia4.network

import online.decodeit.trivia4.model.CategoryList
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface CategoryAPI {
    @GET("categories")
    suspend fun getCategories(): CategoryList
}