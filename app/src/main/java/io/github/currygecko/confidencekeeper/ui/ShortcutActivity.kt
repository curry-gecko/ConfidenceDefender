package io.github.currygecko.confidencekeeper.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SettingsSuggest
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.model.ShortcutSettings
import io.github.currygecko.confidencekeeper.ui.compose.EditTextWithSlider
import io.github.currygecko.confidencekeeper.ui.compose.list.item.AppImage
import io.github.currygecko.confidencekeeper.ui.compose.list.item.AppInfo
import io.github.currygecko.confidencekeeper.ui.theme.ConfidenceKeeperTheme
import io.github.currygecko.confidencekeeper.usecase.MakeShortcutUseCase

class ShortcutActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receivedIntent = intent
        val appInformation =
            receivedIntent.getParcelableExtra(AppInformation.EXTRA_KEY) as? AppInformation

        setContent {
            val info by remember { mutableStateOf(appInformation!!) }
            ConfidenceKeeperTheme {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            MakeShortcutUseCase()(
                                info,
                                ShortcutSettings(0),
                                this
                            )
                        }) {
                            Icon(
                                Icons.Default.SettingsSuggest,
                                contentDescription = "Create Shortcut",
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Column {
                            AppDetail(info)
                            Settings()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AppDetail(appInformation: AppInformation?) {

    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        appInformation?.let {
            AppImage(it)
            AppInfo(it)
        }
    }
}

@Composable
private fun Settings() {
    //
    var sliderValue by remember { mutableStateOf(0f) }
    var textValue by remember { mutableStateOf("0") }
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        EditTextWithSlider(
            sliderValue = sliderValue,
            onSliderValueChange = { value ->
                sliderValue = value
                textValue = value.toInt().toString()
            },
            textValue = textValue,
            onTextValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.toFloatOrNull() != null) {
                    textValue = newValue
                    sliderValue = newValue.toFloatOrNull() ?: 0f
                }
            }
        )
    }
}
