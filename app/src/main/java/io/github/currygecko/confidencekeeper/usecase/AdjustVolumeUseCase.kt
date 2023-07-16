package io.github.currygecko.confidencekeeper.usecase

import android.content.Context
import android.media.AudioManager
import android.os.Build
import kotlin.math.roundToInt

class AdjustVolumeUseCase() {

    operator fun invoke(context: Context, volume: Int) {
        val manager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        val settingVolume: Int =
            (manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
                .toDouble() * (volume.toDouble() / 100.0)).roundToInt()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            manager.setStreamVolume(
                AudioManager.STREAM_MUSIC,
                settingVolume, 0
            )
        }

        CallAudioShowInterfaceUseCase().invoke(context)
    }
}