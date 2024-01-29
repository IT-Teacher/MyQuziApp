package uz.itteacher.myquziapp11_05_2.models

data class QuizDto(
    val id: Int = 0,
    val questionText: String,
    val options: List<OptionDto>
)