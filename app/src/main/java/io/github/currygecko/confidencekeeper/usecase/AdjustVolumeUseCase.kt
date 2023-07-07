package io.github.currygecko.confidencekeeper.usecase

import android.content.Context
import android.media.AudioManager
import android.os.Build

class AdjustVolumeUseCase() {

    operator fun invoke(context: Context) {
        val manager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            manager.setStreamVolume(
                AudioManager.STREAM_MUSIC,
                manager.getStreamMinVolume(AudioManager.STREAM_MUSIC), 0
            )
        }
    }
}