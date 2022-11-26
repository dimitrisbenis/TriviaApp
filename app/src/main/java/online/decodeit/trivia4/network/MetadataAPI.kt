package online.decodeit.trivia4.network

import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface MetadataAPI {
    @GET("metadata")
    suspend fun getMetadata(): Metadata
}