package uz.itteacher.myquziapp11_05_2.screens.quiz

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.itteacher.myquziapp11_05_2.models.OptionDto
import uz.itteacher.myquziapp11_05_2.models.QuizDto

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    private val quizModel = QuizModel()
    private val quizList = questionList()

    val count = quizList.size
    var result = 0
    val time = "3"

    private val f = 1f / count

    private var _progress = MutableLiveData(f)
    var progress: LiveData<Float> = _progress

    private var _numberQuetion = MutableLiveData(1)
    var numberQuetion: LiveData<Int> = _numberQuetion

    private var _timeprogress = MutableLiveData(time)
    var timeprogress: LiveData<String> = _timeprogress

    private var _status = MutableLiveData(false)
    var status: LiveData<Boolean> = _status

    private var _quiz = MutableLiveData(quizList[0])
    var quiz: LiveData<QuizDto> = _quiz

    fun onProgress(choice: String) {
        if (_numberQuetion.value!! <= count)
            if (checkAnswer(choice, _quiz.value!!)) {
                result++
            }
        if (count > _numberQuetion.value!!) {

            _numberQuetion.value = _numberQuetion.value?.plus(1)
            _progress.value = _progress.value?.plus(f)
            _quiz.value = quizList[_numberQuetion.value?.minus(1)!!]
        }
    }

    private fun checkAnswer(choice: String, quizDto: QuizDto): Boolean {
        var f = false
        for (i in quizList) {
            if (i == quizDto)
                for (j in i.options) {
                    if (!j.status && j.optionText == choice) {
                        j.status = true
                        if (j.correctAnswer && j.index != 0) {
                            j.index = 0
                            f= true
                        }
                    }else {
                        if (j.status && j.correctAnswer){
                            j.index = -1
                            result--
                        }
                        j.status = false
                    }

                }
        }
        return f
    }

    fun startTime() {
        val totalTime = time.toInt() * 60 * 1000L
        object : CountDownTimer(totalTime, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                _timeprogress.value = String.format("%02d:%02d", minutes, remainingSeconds)
            }

            override fun onFinish() {
                _status.value = true
            }

        }.start()
    }

    fun prevQuestion(){
        if (1 < _numberQuetion.value!!) {
            _numberQuetion.value = _numberQuetion.value?.minus(1)
            _quiz.value = quizList[_numberQuetion.value?.minus(1)!!]
        }
    }


    private fun questionList(): List<QuizDto> {
        return quizModel.getQuizList()

    }
}