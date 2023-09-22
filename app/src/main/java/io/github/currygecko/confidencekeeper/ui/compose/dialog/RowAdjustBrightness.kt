package io.github.currygecko.confidencekeeper.ui.compose.dialog

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.github.currygecko.confidencekeeper.ui.RequestPermissionActivity
import io.github.currygecko.confidencekeeper.ui.compose.ContentButton
import io.github.currygecko.confidencekeeper.ui.compose.findActivity
import io.github.currygecko.confidencekeeper.usecase.AdjustBrightnessUseCase

@Composable
fun RowAdjustBrightness() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            Icons.Filled.SettingsBrightness,
            contentDescription = "Brightness",
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        ContentButton(modifier = Modifier.weight(1f), text = "MIN") {
            tryAdjustBrightness(
                context,
                1
            )
        }
        ContentButton(modifier = Modifier.weight(1f), text = "15%") {
            tryAdjustBrightness(
                context,
                15
            )
        }
        ContentButton(modifier = Modifier.weight(1f), text = "50%") {
            tryAdjustBrightness(
                context,
                50
            )
        }
    }

}

private fun tryAdjustBrightness(context: Context, brightness: Int) {
    val activity = context.findActivity() ?: return
    if (Settings.System.canWrite(context)) {
        AdjustBrightnessUseCase().invoke(activity, brightness)
    } else {
        val intent = Intent(activity, RequestPermissionActivity::class.java)
        context.startActivity(intent)
    }
}
