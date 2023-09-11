package io.github.currygecko.confidencekeeper.ui.compose.list

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.ui.ShortcutActivity
import io.github.currygecko.confidencekeeper.ui.compose.list.item.SimpleAppInfoItemView

@Composable
fun AppInfoListView(pagingItems: List<AppInformation>, columns: Int = 4) {
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(pagingItems.count()) { index ->
            val item = pagingItems[index]
//            CustomItemView(item = item, listener = object : ClickItemViewListener {
            SimpleAppInfoItemView(item = item, listener = object : ClickItemViewListener {
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

                override fun onMakeShortcutButtonClick(appInformation: AppInformation) {
                    val intent = Intent(context, ShortcutActivity::class.java).apply {
                        putExtra(AppInformation.EXTRA_KEY, appInformation)
                    }
                    context.startActivity(intent)
                }
            })
        }
    }
}
