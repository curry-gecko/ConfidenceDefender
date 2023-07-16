package io.github.currygecko.confidencekeeper.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.usecase.AdjustVolumeUseCase
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
                        TextButton(onClick = {}) {
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
        Row(
            modifier = Modifier
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                Icons.Filled.VolumeUp,
                contentDescription = "Speaker",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            ContentButton(text = "MUTE") { AdjustVolumeUseCase().invoke(context, 0) }
            ContentButton(text = "15%") { AdjustVolumeUseCase().invoke(context, 15) }
            ContentButton(text = "50%") { AdjustVolumeUseCase().invoke(context, 50) }
        }
    }
}

@Composable
private fun ContentButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text, maxLines = 1)
    }
}
