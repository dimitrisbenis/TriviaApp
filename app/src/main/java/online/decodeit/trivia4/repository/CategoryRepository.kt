package online.decodeit.trivia4.repository

import online.decodeit.trivia4.data.DataOrException
import online.decodeit.trivia4.model.CategoryList
import online.decodeit.trivia4.model.QuestionItem
import online.decodeit.trivia4.network.CategoryAPI
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryAPI: CategoryAPI) {

    private val listOfCategories = DataOrException<
            CategoryList,
            Boolean, Exception >()
}