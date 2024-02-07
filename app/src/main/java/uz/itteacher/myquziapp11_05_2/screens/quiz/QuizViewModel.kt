package uz.itteacher.myquziapp11_05_2.screens.quiz

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import uz.itteacher.myquziapp11_05_2.models.OptionDto
import uz.itteacher.myquziapp11_05_2.models.QuizDto
import uz.itteacher.myquziapp11_05_2.navigation.Screens

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    private val quizModel = QuizModel()
    var quizList = mutableListOf<QuizDto>(QuizDto("1", "", mutableListOf(OptionDto("", ""), OptionDto("", ""), OptionDto("", ""), OptionDto("", ""), )))

    init {
        quizModel.getQuizList1 {
            if (quizList[0].id == "1") {
                quizList.clear()
                _quiz.value = it
            }
            quizList.add(it)
            _numberQuetion.value = _numberQuetion.value!!.plus(1)
        }
    }

    val count = quizList.size
    val time = "3"

    private val f = 1f / count

    private var _progress = MutableLiveData(0f)
    var progress: LiveData<Float> = _progress

    private var _numberQuetion = MutableLiveData(0)
    var numberQuetion: LiveData<Int> = _numberQuetion

    private var _timeprogress = MutableLiveData(time)
    var timeprogress: LiveData<String> = _timeprogress

    private var _status = MutableLiveData(false)
    var status: LiveData<Boolean> = _status

    private var _quiz = MutableLiveData(quizList[_numberQuetion.value!!])
    var quiz: LiveData<QuizDto> = _quiz

    private val _showDialog = MutableLiveData(false)
    val showDialog: LiveData<Boolean> = _showDialog

    fun onOpenDialogClicked() {
        _showDialog.value = true
    }

    fun onDialogConfirm(navController: NavController) {
        _showDialog.value = false
        navController.navigate(Screens.Result.route)

    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    fun onProgress() {
        _quiz.value?.options!!.forEach {
            if (it.status) return
        }
        _progress.value = _progress.value?.plus(f)
    }

    fun checkQuestion(choice: String) {
        onProgress()
        _quiz.value?.options!!.forEach {
            it.status = choice == it.optionText
        }
    }

    fun nextQuestion() {
        if (_numberQuetion.value!! < count - 1) {
            _numberQuetion.value = _numberQuetion.value?.plus(1)
            _quiz.value = quizList[_numberQuetion.value!!]
        }
    }

    fun prevQuestion() {
        if (_numberQuetion.value!! > 0) {
            _numberQuetion.value = _numberQuetion.value?.minus(1)
            _quiz.value = quizList[_numberQuetion.value!!]
        }
    }

    fun result():Int {
        var result = 0
        quizList.forEach { it ->
            it.options.forEach {
                if (it.status && it.correctAnswer) {
                    result++
                }
            }
        }
        return result
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

//    private fun questionList(): List<QuizDto> {
//        return quizModel.getQuizList1()
//    }
}