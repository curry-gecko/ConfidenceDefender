package io.github.currygecko.confidencekeeper.ui.compose

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import io.github.currygecko.confidencekeeper.model.AppInformation

@Composable
fun AppInfoListView(pagingItems: List<AppInformation>) {
    val context = LocalContext.current
    LazyColumn {
        items(pagingItems.count()) { index ->
            val item = pagingItems[index]
            CustomItemView(item = item, listener = ClickItemViewListener(
                onClickListener = {
                    // TODO
                },
                onLongClickListener = {
                    Toast.makeText(context, item.packageLabel, Toast.LENGTH_SHORT).show()
                }
            ))
        }
    }

}
