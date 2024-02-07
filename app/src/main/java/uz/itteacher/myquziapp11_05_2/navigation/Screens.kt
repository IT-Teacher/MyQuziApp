package uz.itteacher.myquziapp11_05_2.navigation

sealed class Screens(var route: String) {
    object Home: Screens("home_screen")
    object Result: Screens("result_screen")
    object Quiz: Screens("quiz_screen")
}