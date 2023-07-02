package io.github.currygecko.confidencekeeper.ui.compose.list.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.ui.compose.color.ForDarkTheme
import io.github.currygecko.confidencekeeper.ui.compose.list.ClickItemViewListener


@Composable
fun AppButtons(item: AppInformation, listener: ClickItemViewListener?, modifier: Modifier) {
    Row(  // Row for buttons
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { listener?.onSettingsButtonClick(item.packageName) },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Settings Icon",
                modifier = Modifier.size(24.dp),
                tint = ForDarkTheme.IconButtonOutline
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = { listener?.onMakeShortcutButtonClick(item.packageName) },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowForward,
                contentDescription = "Make Shortcut",
                modifier = Modifier.size(24.dp),
                tint = ForDarkTheme.IconButtonOutline
            )
        }
    }
}
