package io.github.currygecko.confidencekeeper.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShortcutSettings(
    val volume: Int
) : Parcelable {
    companion object {
        private const val prefix = "FOR_SHORTCUT_KEY"
        const val EXTRA_KEY_VOLUME = "{$prefix}_VOLUME"
    }
}
