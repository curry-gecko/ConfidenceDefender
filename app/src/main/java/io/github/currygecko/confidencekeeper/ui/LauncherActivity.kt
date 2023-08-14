package io.github.currygecko.confidencekeeper.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.ui.compose.dialog.RowAdjustVolume
import io.github.currygecko.confidencekeeper.usecase.CallAudioShowInterfaceUseCase

class LauncherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            AdjustVolumeUseCase()(this)
            var showDialog by remember { mutableStateOf(true) }

            if (showDialog) {
                CallAudioShowInterfaceUseCase().invoke(applicationContext)
                AlertDialog(
                    onDismissRequest = {
                        finish()
                        showDialog = false
                    },
                    title = { Text("Launch") },
                    text = {
                        AlertDialogContent()
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            launchIntent()
                            showDialog = false
                        }) {
                            Text("Launch")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            finish()
                        }) {
                            Text("Dismiss")
                        }
                    }
                )
            }

        }
    }

    private fun launchIntent() {
        val intent = intent
        val extras = intent.extras ?: return
//        val settings = extras.getParcelableArrayList<AppInformation>("settings")
        val packageName = extras.getString(AppInformation.EXTRA_KEY) ?: return

        val launchIntent = packageManager.getLaunchIntentForPackage(packageName) ?: return
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(launchIntent)
        finish()
    }
}

@Composable
private fun AlertDialogContent() {
    val context = LocalContext.current
    Column {
        Text("I will change the settings and launch the app.")
        RowAdjustVolume()
    }
}

