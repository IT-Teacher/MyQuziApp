package uz.itteacher.myquziapp11_05_2.screens.quiz

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import uz.itteacher.myquziapp11_05_2.models.OptionDto
import uz.itteacher.myquziapp11_05_2.models.QuizDto
import javax.security.auth.callback.Callback

private const val TAG = "QuizModel"
class QuizModel {
    var quizList = mutableListOf<QuizDto>()
    private val db = Firebase.firestore


    init {

        val optionList = mutableListOf<OptionDto>()
        optionList.add(OptionDto(optionText = "9"))
        optionList.add(OptionDto(optionText = "8", correctAnswer = true))
        optionList.add(OptionDto(optionText = "11"))
        optionList.add(OptionDto(optionText = "7"))
        quizList.add(QuizDto(questionText = "2 + 6 = ?", options = optionList))
//        val optionList1 = mutableListOf<OptionDto>()
//        optionList1.add(OptionDto(optionText = "32"))
//        optionList1.add(OptionDto(optionText = "33", correctAnswer = true))
//        optionList1.add(OptionDto(optionText = "23"))
//        optionList1.add(OptionDto(optionText = "44"))
//        quizList.add(QuizDto(questionText = "11 * 3 = ?", options = optionList1))
//        val optionList2 = mutableListOf<OptionDto>()
//        optionList2.add(OptionDto(optionText = "54"))
//        optionList2.add(OptionDto(optionText = "55", correctAnswer = true))
//        optionList2.add(OptionDto(optionText = "50"))
//        optionList2.add(OptionDto(optionText = "56"))
//        quizList.add(QuizDto(questionText = "32 + 23 = ?", options = optionList2))
//        val optionList3 = mutableListOf<OptionDto>()
//        optionList3.add(OptionDto(optionText = "10"))
//        optionList3.add(OptionDto(optionText = "9", correctAnswer = true))
//        optionList3.add(OptionDto(optionText = "7"))
//        optionList3.add(OptionDto(optionText = "8"))
//        quizList.add(QuizDto(questionText = "6 + 3 = ?", options = optionList3))
//        val optionList4 = mutableListOf<OptionDto>()
//        optionList4.add(OptionDto(optionText = "32"))
//        optionList4.add(OptionDto(optionText = "33", correctAnswer = true))
//        optionList4.add(OptionDto(optionText = "23"))
//        optionList4.add(OptionDto(optionText = "44"))
//        quizList.add(QuizDto(questionText = "11 + 22 = ?", options = optionList4))
    }

    fun getQuizList1(callback:(QuizDto) ->Unit) {
        val reference = db.collection("QuizDto")
        val quizDtoList = mutableListOf<QuizDto>()

        val optionsList = mutableListOf<OptionDto>()
        optionsList.add(OptionDto(optionText = "9"))
        optionsList.add(OptionDto(optionText = "8", correctAnswer = true))
        optionsList.add(OptionDto(optionText = "11"))
        optionsList.add(OptionDto(optionText = "7"))

        quizDtoList.add(QuizDto(questionText = "2 + 6 = ?", options = optionsList))

        reference.get().addOnSuccessListener { result1 ->
            for (quizDto in result1) {
                reference.document(quizDto.id).collection("OptionDto")
                    .get()
                    .addOnSuccessListener { result ->
                        val quizText = quizDto["QuizText"] as String
                        val optionList: MutableList<OptionDto> = mutableListOf()
                        for (optionDto in result) {
                            val optionText = optionDto["OptionText"] as String
                            val status = optionDto["status"] as Boolean
                            val correctAnswer = optionDto["correctAnswer"] as Boolean
                            optionList.add(
                                OptionDto(
                                    optionDto.id,
                                    optionText,
                                    status,
                                    correctAnswer
                                )
                            )
                        }
                        val quiz = QuizDto(quizDto.id, quizText, optionList)
                        quizDtoList.add(quiz)

                        callback(quiz)

                    }
            }
        }
    }
}