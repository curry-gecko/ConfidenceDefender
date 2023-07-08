package io.github.currygecko.confidencekeeper.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.currygecko.confidencekeeper.model.AppInformation

class LauncherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            AdjustVolumeUseCase()(this)
            var showDialog by remember { mutableStateOf(true) }

            if (showDialog) {
                showDialog = false;
                launchIntent()
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