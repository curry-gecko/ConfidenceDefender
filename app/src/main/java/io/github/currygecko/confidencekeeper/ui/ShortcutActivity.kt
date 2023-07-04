package io.github.currygecko.confidencekeeper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.ui.compose.EditTextWithSlider
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
            // Add state here
            var sliderValue by remember { mutableStateOf(0f) }
            var textValue by remember { mutableStateOf("0") }
            ConfidenceKeeperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Row(modifier = Modifier.padding(vertical = 8.dp)) {
                            appInformation?.let {
                                AppImage(it)
                                AppInfo(it)
                            }
                        }
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
                }
            }
        }
    }
}
