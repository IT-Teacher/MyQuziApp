package uz.itteacher.myquziapp11_05_2.screens.quiz

import uz.itteacher.myquziapp11_05_2.models.OptionDto
import uz.itteacher.myquziapp11_05_2.models.QuizDto

class QuizModel {
    private val quizList = mutableListOf<QuizDto>()

    init {
        val optionList = mutableListOf<OptionDto>()
        optionList.add(OptionDto(optionText = "9"))
        optionList.add(OptionDto(optionText = "8", correctAnswer = true))
        optionList.add(OptionDto(optionText = "11"))
        optionList.add(OptionDto(optionText = "7"))
        quizList.add(QuizDto(questionText = "2 + 6 = ?", options =optionList))
        val optionList1 = mutableListOf<OptionDto>()
        optionList1.add(OptionDto(optionText = "32"))
        optionList1.add(OptionDto(optionText = "33", correctAnswer = true))
        optionList1.add(OptionDto(optionText = "23"))
        optionList1.add(OptionDto(optionText = "44"))
        quizList.add(QuizDto(questionText = "11 * 3 = ?", options =optionList1))
        val optionList2 = mutableListOf<OptionDto>()
        optionList2.add(OptionDto(optionText = "54"))
        optionList2.add(OptionDto(optionText = "55", correctAnswer = true))
        optionList2.add(OptionDto(optionText = "50"))
        optionList2.add(OptionDto(optionText = "56"))
        quizList.add(QuizDto(questionText = "32 + 23 = ?", options =optionList2))
        val optionList3 = mutableListOf<OptionDto>()
        optionList3.add(OptionDto(optionText = "10"))
        optionList3.add(OptionDto(optionText = "9", correctAnswer = true))
        optionList3.add(OptionDto(optionText = "7"))
        optionList3.add(OptionDto(optionText = "8"))
        quizList.add(QuizDto(questionText = "6 + 3 = ?", options =optionList3))
        val optionList4 = mutableListOf<OptionDto>()
        optionList4.add(OptionDto(optionText = "32"))
        optionList4.add(OptionDto(optionText = "33", correctAnswer = true))
        optionList4.add(OptionDto(optionText = "23"))
        optionList4.add(OptionDto(optionText = "44"))
        quizList.add(QuizDto(questionText = "11 + 22 = ?", options =optionList4))
    }

    fun getQuizList():MutableList<QuizDto>{
        return quizList
    }
}