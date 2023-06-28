package io.github.currygecko.confidencekeeper.ui.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.currygecko.confidencekeeper.model.AppInfo

@Composable
fun AppInfoListView(pagingItems: List<AppInfo>) {
    LazyColumn {
        items(pagingItems.count()) { index ->
            val item = pagingItems[index]
            Text(text = item.packageName)
        }
    }
}
