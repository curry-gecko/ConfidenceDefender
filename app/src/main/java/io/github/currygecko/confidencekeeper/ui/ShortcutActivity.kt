package io.github.currygecko.confidencekeeper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.ui.compose.list.item.AppImage
import io.github.currygecko.confidencekeeper.ui.compose.list.item.AppInfo
import io.github.currygecko.confidencekeeper.ui.theme.ConfidenceKeeperTheme

class ShortcutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receivedIntent = intent
        val appInformation =
            receivedIntent.getParcelableExtra(AppInformation.EXTRA_KEY) as? AppInformation

        setContent {
            ConfidenceKeeperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Row(modifier = Modifier.padding(vertical = 8.dp)) {
                        appInformation?.let {
                            AppImage(it)
                            AppInfo(it)
                        }
                    }
                }
            }
        }
    }
}