package io.github.currygecko.confidencekeeper.ui.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AppInfoListView(pagingItems: List<String>) {
    LazyColumn {
        items(pagingItems.count()) { index ->
            val item = pagingItems[index]
            Text(text = item)
        }
    }
}
