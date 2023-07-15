package io.github.currygecko.confidencekeeper.usecase

import android.content.Context
import android.media.AudioManager

class CallAudioShowInterfaceUseCase {
    operator fun invoke(context: Context) {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustStreamVolume(
            AudioManager.STREAM_MUSIC,
            AudioManager.ADJUST_SAME,
            AudioManager.FLAG_SHOW_UI
        )

    }

}