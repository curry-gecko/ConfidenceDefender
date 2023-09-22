package io.github.currygecko.confidencekeeper.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextWithSlider(
    sliderValue: Float,
    onSliderValueChange: (Float) -> Unit,
    textValue: String,
    onTextValueChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = textValue,
            onValueChange = { newValue ->
                onTextValueChange(newValue)
                onSliderValueChange(newValue.toFloatOrNull() ?: 0f)
            },
            label = { Text("Number") },
//            colors = MaterialTheme.outlinedTextFieldColors()
        )

        Slider(
            value = sliderValue,
            onValueChange = { newValue ->
                onSliderValueChange(newValue)
                onTextValueChange(newValue.toInt().toString())
            },
            valueRange = 0f..100f
        )
    }
}
