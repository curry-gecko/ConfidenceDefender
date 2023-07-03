package io.github.currygecko.confidencekeeper.ui.compose.list.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import io.github.currygecko.confidencekeeper.model.AppInformation

@Composable
fun AppImage(item: AppInformation) {
    val packageManager = LocalContext.current.packageManager
    val imageBitmap = remember {
        item.getAppIcon(packageManager).toBitmap().asImageBitmap()
    } // TODO パフォーマンスを考慮した実装
    Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {
        Image(
            bitmap = imageBitmap,
            contentDescription = item.packageLabel,
            modifier = Modifier.size(48.dp)
        )
    }
}
