package io.github.currygecko.confidencekeeper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.currygecko.confidencekeeper.usecase.AdjustVolumeUseCase

class LauncherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdjustVolumeUseCase()(this)
            var showDialog by remember { mutableStateOf(true) }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Notification") },
                    text = { Text("This is a notification.") },
                    confirmButton = {
                        Button(onClick = {
                            showDialog = false
                            launchIntent()
                        }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }

    private fun launchIntent() {
        val packageName = "org.mozilla.firefox"
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            startActivity(intent)
            finish()
        }
    }
}