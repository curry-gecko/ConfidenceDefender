package io.github.currygecko.confidencekeeper.ui.compose.list.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.ui.compose.color.ForDarkTheme
import io.github.currygecko.confidencekeeper.ui.compose.list.ClickItemViewListener

// ランチャーショートカット作成対象アプリリスト表示
@Composable
fun SimpleAppInfoItemView(item: AppInformation, listener: ClickItemViewListener?) {
    val colorBorder = ForDarkTheme.Border
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
//            .border(
//                width = 2.dp,
//                color = colorBorder
//            )
            .clickable { listener?.onClick() }
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        listener?.onLongClick()
                    }
                )
            }
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            AppImage(item)
//            AppInfo(item)
//            AppButtons(item, listener, Modifier.padding(8.dp))
        }
    }
}
