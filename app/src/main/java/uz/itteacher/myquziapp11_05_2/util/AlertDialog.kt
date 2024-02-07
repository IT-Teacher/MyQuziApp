package uz.itteacher.myquziapp11_05_2.util

import android.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun MyDialog(
    show: Boolean,
    text: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { false },
            confirmButton = {
                TextButton(onClick = onConfirm)
                { Text(text = "OK") }
            },
            dismissButton = {
                TextButton(onClick = onDismiss)
                { Text(text = "Cancel") }
            },
            text = { Text(text = text) }
        )
    }
}
