package uz.itteacher.myquziapp11_05_2.screens.quiz

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.itteacher.myquziapp11_05_2.R
import uz.itteacher.myquziapp11_05_2.ui.theme.Green
import uz.itteacher.myquziapp11_05_2.ui.theme.MainBg
import uz.itteacher.myquziapp11_05_2.ui.theme.OptionBg
import uz.itteacher.myquziapp11_05_2.ui.theme.TextColor
import uz.itteacher.myquziapp11_05_2.util.MyDialog
import javax.security.auth.callback.Callback

@OptIn(ExperimentalMaterial3Api::class)
private const val TAG = "QuizView"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizView(viewModel: QuizViewModel, navController: NavController) {
    val progress = viewModel.progress.observeAsState().value
    val numberQuetion = viewModel.numberQuetion.observeAsState().value
    val timeprogress = viewModel.timeprogress.observeAsState().value
    val status = viewModel.status.observeAsState().value
    val quiz = viewModel.quiz.observeAsState().value

    val showDialogState = viewModel.showDialog.observeAsState().value




    if (status != null && status) {
        viewModel.onOpenDialogClicked()
        if (showDialogState != null) {
            MyDialog(
                show = showDialogState,
                text = "Vaqt tugadi",
                onDismiss = { viewModel.onDialogConfirm(navController) },
                onConfirm = { viewModel.onDialogConfirm(navController) }
            )
        }
    }




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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "back",
                    modifier = Modifier.clickable {
                        viewModel.prevQuestion()
                    }
                )
                Text(
                    text = "Math Quiz",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = TextColor
                )
                Text(
                    text = "skip",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = OptionBg
                )

            }
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                if (progress != null) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = Green,
                        trackColor = OptionBg,
                        strokeCap = StrokeCap.Round,
                        progress = progress
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${numberQuetion?.plus(1)}",
                        color = Green,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "/${viewModel.count}",
                        color = OptionBg,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painterResource(id = R.drawable.time_icon),
                        contentDescription = "time icon"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    if (timeprogress != null) {
                        Text(
                            text = timeprogress,
                            color = OptionBg,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            if (quiz != null) {
                Text(
                    text = quiz.questionText,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    color = TextColor
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                if (quiz != null) {
                    items(quiz.options.size) {

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor =
                                if (quiz.options[it].status)
                                    Green
                                else
                                    OptionBg
                            ),
                            onClick = {
                                viewModel.checkQuestion(quiz.options[it].optionText)
                            },
                        ) {
                            Text(
                                text = quiz.options[it].optionText,
                                Modifier.padding(20.dp),
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "back",
                    modifier = Modifier.clickable {
                        viewModel.prevQuestion()
                    })
                Icon(painter = painterResource(id = R.drawable.arrow_forward),
                    contentDescription = "back",
                    modifier = Modifier.clickable {
                        viewModel.nextQuestion()
                    })
            }

            Spacer(modifier = Modifier.height(16.dp))
            if (progress == 1f){
                if (showDialogState != null) {
                    MyDialog(
                        show = showDialogState,
                        text = "Testni yakunlashni hohlaysizmi?",
                        onDismiss = viewModel::onDialogDismiss,
                        onConfirm = { viewModel.onDialogConfirm(navController) }
                    )
                }
                Button(onClick = {
                    viewModel.onOpenDialogClicked()
                }) {
                    Text(text = "Yakunlash")
                }
            }

        }
    }
}

