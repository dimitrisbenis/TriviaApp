package online.decodeit.trivia4.repository

import android.util.Log
import online.decodeit.trivia4.data.DataOrException
import online.decodeit.trivia4.model.CategoryList
import online.decodeit.trivia4.network.CategoryAPI
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryAPI: CategoryAPI) {

    private val listOfCategories = DataOrException<
            CategoryList,
            Boolean, Exception >()

    suspend fun getCategories(): DataOrException<CategoryList,
            Boolean, Exception> {
        try {
            listOfCategories.loading = true
            listOfCategories.data = categoryAPI.getCategories()

            if (listOfCategories.data.toString().isNotEmpty())
                listOfCategories.loading = false

        } catch (ex: Exception) {
            listOfCategories.e = ex
            Log.d("Exception", "getQuestions: ${ex.localizedMessage}")
        }
        return listOfCategories
    }
}