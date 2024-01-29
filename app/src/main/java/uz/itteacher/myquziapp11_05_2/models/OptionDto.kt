package uz.itteacher.myquziapp11_05_2.models

data class OptionDto(
    val id: Int = 0,
    val optionText: String,
    val correctAnswer: Boolean = false,
    var status: Boolean = false,
    var index:Int = -1
)