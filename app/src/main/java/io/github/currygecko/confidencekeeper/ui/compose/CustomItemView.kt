package io.github.currygecko.confidencekeeper.ui.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import io.github.currygecko.confidencekeeper.model.AppInfo
import io.github.currygecko.confidencekeeper.ui.compose.color.ForDarkTheme
import io.github.currygecko.confidencekeeper.ui.compose.layout.FontSize

@Composable
fun CustomItemView(item: AppInfo, listener: ClickItemViewListener?) {
    val textSize = FontSize.Large
    val colorBorder = ForDarkTheme.Border
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = colorBorder
            )
            .clickable { listener?.onClick() }
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        listener?.onLongClick()
                    }
                )
            },
        verticalAlignment = Alignment.CenterVertically

    ) {

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
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
}