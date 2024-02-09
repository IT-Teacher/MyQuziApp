package uz.itteacher.myquziapp11_05_2.screens.quiz

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import uz.itteacher.myquziapp11_05_2.models.OptionDto
import uz.itteacher.myquziapp11_05_2.models.QuizDto
import javax.security.auth.callback.Callback

private const val TAG = "QuizModel"
class QuizModel {
    private val db = Firebase.firestore
    fun getQuizList(callback:(QuizDto) ->Unit) {
        val reference = db.collection("QuizDto")
        val quizDtoList = mutableListOf<QuizDto>()

        reference.get().addOnSuccessListener { result1 ->
            for (quizDto in result1) {
                val optionList: MutableList<OptionDto> = mutableListOf()
                val quizText = quizDto["QuizText"] as String
                val quiz = QuizDto(quizDto.id, quizText, optionList)
                reference.document(quizDto.id).collection("OptionDto")
                    .get()
                    .addOnSuccessListener { result ->
                        for (optionDto in result) {
                            val optionText = optionDto["OptionText"] as String
                            val status = optionDto["status"] as Boolean
                            val correctAnswer = optionDto["correctAnswer"] as Boolean
                            val option=OptionDto(optionDto.id, optionText, correctAnswer, status)
                            quiz.options.add(option)
                        }

                        callback(quiz)
                    }
//                Log.d(TAG, "${quiz.options}")

            }

        }
    }
}