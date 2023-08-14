package io.github.currygecko.confidencekeeper.ui.compose

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ContentButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text, maxLines = 1)
    }
}