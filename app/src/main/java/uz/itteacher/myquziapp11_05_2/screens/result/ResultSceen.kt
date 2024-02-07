package uz.itteacher.myquziapp11_05_2.screens.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.itteacher.myquziapp11_05_2.models.QuizDto
import uz.itteacher.myquziapp11_05_2.screens.quiz.QuizView
import uz.itteacher.myquziapp11_05_2.screens.quiz.QuizViewModel
import uz.itteacher.myquziapp11_05_2.ui.theme.ErrorBg
import uz.itteacher.myquziapp11_05_2.ui.theme.Green
import uz.itteacher.myquziapp11_05_2.ui.theme.MainBg
import uz.itteacher.myquziapp11_05_2.ui.theme.OptionBg
import uz.itteacher.myquziapp11_05_2.ui.theme.TextColor

@Composable
fun ResultScreen(viewModel: QuizViewModel, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Savollar soni: ${viewModel.count}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = TextColor,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "To'g'ri javoblar soni: ${viewModel.result()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = TextColor,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }


            LazyColumn{
                items(viewModel.count) {
                    val v = viewModel.quizList[0]
                    ViewQuiz(v)
                }
            }
        }
    }
}

@Composable
fun ViewQuiz(quizDto: QuizDto){
    Text(
        text = quizDto.questionText,
        fontSize = 20.sp,
        textAlign = TextAlign.Justify,
        color = TextColor
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyColumn(modifier = Modifier.fillMaxWidth().height(250.dp)) {
        items(quizDto.options.size) {
            var color = OptionBg
            if (quizDto.options[it].status){
                color = ErrorBg
            }
            if (quizDto.options[it].correctAnswer){ 
                color = Green
            }
            ItemOption(option = quizDto.options[it].optionText, 
                color = color)
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}
@Composable
fun ItemOption(option: String, color: Color) {
    Card(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 6.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = option,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight(800),
            modifier = Modifier
                .background(color)
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth()
        )
    }
}