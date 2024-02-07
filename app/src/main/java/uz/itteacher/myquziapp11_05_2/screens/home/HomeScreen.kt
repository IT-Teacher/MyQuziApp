package uz.itteacher.myquziapp11_05_2.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uz.itteacher.myquziapp11_05_2.navigation.Screens

@Composable
fun HomeScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { navController.navigate(Screens.Quiz.route) }, modifier = Modifier.padding(20.dp)) {
            Text(text = "Testni boshlash")
        }
    }
}