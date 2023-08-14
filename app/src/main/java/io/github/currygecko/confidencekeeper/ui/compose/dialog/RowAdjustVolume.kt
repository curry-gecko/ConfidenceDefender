package io.github.currygecko.confidencekeeper.ui.compose.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.github.currygecko.confidencekeeper.ui.compose.ContentButton
import io.github.currygecko.confidencekeeper.usecase.AdjustVolumeUseCase

@Composable
fun RowAdjustVolume() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            Icons.Filled.VolumeUp,
            contentDescription = "Speaker",
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        ContentButton(text = "MUTE") { AdjustVolumeUseCase().invoke(context, 0) }
        ContentButton(text = "15%") { AdjustVolumeUseCase().invoke(context, 15) }
        ContentButton(text = "50%") { AdjustVolumeUseCase().invoke(context, 50) }
    }
}