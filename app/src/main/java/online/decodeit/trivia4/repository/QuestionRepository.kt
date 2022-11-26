package online.decodeit.trivia4.repository

import online.decodeit.trivia4.data.DataOrException
import online.decodeit.trivia4.model.QuestionItem
import online.decodeit.trivia4.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor( private val api: QuestionAPI) {

    private val listOfQuestions = DataOrException<
            ArrayList<QuestionItem>,
            Boolean, Exception >()
}