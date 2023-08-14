package io.github.currygecko.confidencekeeper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.currygecko.confidencekeeper.ui.compose.dialog.ShowRequestReasonDialog

class RequestPermissionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowRequestReasonDialog()
        }
    }

}

