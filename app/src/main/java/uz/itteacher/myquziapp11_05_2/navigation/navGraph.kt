package uz.itteacher.myquziapp11_05_2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.itteacher.myquziapp11_05_2.screens.home.HomeScreen
import uz.itteacher.myquziapp11_05_2.screens.quiz.QuizModel
import uz.itteacher.myquziapp11_05_2.screens.quiz.QuizView
import uz.itteacher.myquziapp11_05_2.screens.quiz.QuizViewModel
import uz.itteacher.myquziapp11_05_2.screens.result.ResultScreen

@Composable
fun SetNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        val model = QuizViewModel()
        composable(Screens.Quiz.route) {

            model.startTime()
            QuizView(viewModel = model, navController)
        }
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(Screens.Result.route) {
            ResultScreen(model, navController)
        }
    }
}