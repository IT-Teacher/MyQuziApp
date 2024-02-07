package uz.itteacher.myquziapp11_05_2.models

data class QuizDto(
    val id: String = "",
    val questionText: String,
    val options: MutableList<OptionDto>
)