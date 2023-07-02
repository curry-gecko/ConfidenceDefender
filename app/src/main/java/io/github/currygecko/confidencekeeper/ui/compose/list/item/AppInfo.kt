package io.github.currygecko.confidencekeeper.ui.compose.list.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.ui.compose.layout.FontSize


@Composable
fun AppInfo(item: AppInformation) {
    val textSize = FontSize.Large
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = item.packageLabel,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = textSize,
            )
        )
        Text(
            text = item.packageName,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = textSize,
            ),
            modifier = Modifier
                .padding(8.dp)
        )
    }
}