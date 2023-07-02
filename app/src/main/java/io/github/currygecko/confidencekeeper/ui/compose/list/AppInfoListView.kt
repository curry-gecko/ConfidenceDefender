package io.github.currygecko.confidencekeeper.ui.compose.list

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.ui.compose.list.item.CustomItemView

@Composable
fun AppInfoListView(pagingItems: List<AppInformation>) {
    val context = LocalContext.current
    LazyColumn {
        items(pagingItems.count()) { index ->
            val item = pagingItems[index]
            CustomItemView(item = item, listener = object : ClickItemViewListener {
                override fun onClick() {
                    // TODO
                }

                override fun onLongClick() {
                    Toast.makeText(context, item.packageLabel, Toast.LENGTH_SHORT).show()
                }

                override fun onSettingsButtonClick(packageName: String) {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    context.startActivity(intent)
                }

                override fun onMakeShortcutButtonClick(packageName: String) {
//                    val intent = Intent(context, MakeShortcutActvity::class.java).apply {
//                        putExtra("packageName", packageName)
//                    }
//                    context.startActivity(intent)
                }
            })
        }
    }
}
