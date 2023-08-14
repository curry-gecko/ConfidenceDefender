package io.github.currygecko.confidencekeeper.ui.compose.dialog

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import io.github.currygecko.confidencekeeper.ui.compose.findActivity

@Composable
fun ShowRequestReasonDialog() {
    val showDialogState = remember { mutableStateOf(true) }
    var showDialog = showDialogState.value
    val context = LocalContext.current

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("for brightness adjustment") },
            text = { Text("Please grant permission for brightness adjustment functionality") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    // ユーザーにパーミッションをリクエストする
                    val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                    context.findActivity()?.let {
                        intent.data = Uri.parse("package:" + it.packageName)
                        it.startActivity(intent)

                    }
                }) {
                    Text("Allow")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Deny")
                }
            }
        )
    }

}