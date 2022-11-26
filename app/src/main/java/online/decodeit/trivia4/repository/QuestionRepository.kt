package online.decodeit.trivia4.repository

import android.util.Log
import online.decodeit.trivia4.data.DataOrException
import online.decodeit.trivia4.model.QuestionItem
import online.decodeit.trivia4.network.QuestionAPI
import online.decodeit.trivia4.utilities.Constants.CATEGORIES
import javax.inject.Inject

class QuestionRepository @Inject constructor( private val api: QuestionAPI) {

    private val listOfQuestions = DataOrException<
            ArrayList<QuestionItem>,
            Boolean, Exception >()

    suspend fun getQuestions(): DataOrException<ArrayList<QuestionItem>,
            Boolean, Exception> {
        try {
            listOfQuestions.loading = true
            listOfQuestions.data = api.getQuestions(max = 5, category = CATEGORIES["Science"].toString(), "medium")

            if (listOfQuestions.data.toString().isNotEmpty())
                listOfQuestions.loading = false

        } catch (ex: Exception) {
            listOfQuestions.e = ex
            Log.d("Exception", "getQuestions: ${ex.localizedMessage}")
        }
        return listOfQuestions
    }
}